package com.workify.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.workify.entity.DAOAttendanceInfo;
import com.workify.entity.DAOUser;
import com.workify.entity.DAOUserShiftDetails;
import com.workify.model.AttendanceInfoTO;
import com.workify.model.UserShiftDetailsTO;
import com.workify.repository.AttendanceRepository;
import com.workify.repository.UserRepository;
import com.workify.repository.UserShiftDetailsRepository;

@Service
public class AttendanceInfoService {

	@Autowired
	private AttendanceRepository userAttendance;

	@Autowired
	private UserShiftDetailsRepository shiftRepo;

	@Autowired
	private UserRepository userRepo;

	private DAOUser getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		DAOUser user = userRepo.findByUsername(name);
		return user;
	}

	public String updateUserPunch(AttendanceInfoTO punchOutUpdate) {
		DAOUser user = getUserDetails();
		DAOAttendanceInfo punchDetails = userAttendance.findByUserIdAndDate(user.getUserId(), getCurrentDate());

		punchDetails.setActualOut(punchOutUpdate.getActualOut());
		userAttendance.save(punchDetails);
		return "Punch Out Updated";
		// return "Not able to Update Punch Out!!";
	}

	public boolean saveAttendance(AttendanceInfoTO attendanceInfo) {
		DAOUser user = getUserDetails();
		DAOAttendanceInfo punchDetails = userAttendance.findByUserIdAndDate(user.getUserId(), getCurrentDate());

		if (punchDetails != null)
			return false;
		else {
			DAOAttendanceInfo attendanceDetails = new DAOAttendanceInfo();
			attendanceDetails.setUserId(attendanceInfo.getUserId());
			attendanceDetails.setLocationInfo(attendanceInfo.getLocationInfo());
			attendanceDetails.setEmpCode(attendanceInfo.getEmpCode());
			attendanceDetails.setStatus(null);
			attendanceDetails.setDate(getCurrentDate());
			attendanceDetails.setActualIn(attendanceInfo.getActualIn());
			attendanceDetails.setActualOut(null);
			attendanceDetails.setCreationDate(attendanceInfo.getCreationDate());
			attendanceDetails.setModifiedDate(attendanceInfo.getModifiedDate());
			attendanceDetails.setCreatedBy(attendanceInfo.getCreatedBy());
			attendanceDetails.setModifiedBy(attendanceInfo.getModifiedBy());
			userAttendance.save(attendanceDetails);
		}

		return true;
	}

	public DAOUserShiftDetails saveUserShift(UserShiftDetailsTO userShiftTO) {

		DAOUserShiftDetails userShift = new DAOUserShiftDetails();
		userShift.setAttendanceId(userShiftTO.getAttendanceId());
		userShift.setOrgId(userShiftTO.getOrgId());
		userShift.setUserId(userShiftTO.getUserId());
		userShift.setEmpCode(userShiftTO.getEmpCode());
		userShift.setExpIn(userShiftTO.getExpIn());
		userShift.setExpOut(userShiftTO.getExpOut());
		userShift.setCreationDate(userShiftTO.getCreationDate());
		userShift.setModifiedDate(userShiftTO.getModifiedDate());
		userShift.setCreatedBy(userShiftTO.getCreatedBy());
		userShift.setModifiedBy(userShiftTO.getModifiedBy());
		return shiftRepo.save(userShift);
	}

	public List<Map<String, Object>> getAttendanceDetails() {

		DAOUser user = getUserDetails();
		Integer userId = user.getUserId();
		// List<Map<String,Object>>
		// System.out.println("User Id is =" + userId);
		List<Map<String, Object>> attenList = new ArrayList<Map<String, Object>>();
		Date expIn = shiftRepo.findByUserId(userId).getExpIn();
		Date expOut = shiftRepo.findByUserId(userId).getExpOut();
		Integer shiftId = shiftRepo.findByUserId(userId).getUsershiftId();
		long expDiff = calculateTimeDiff(expIn, expOut);
		// System.out.println("Expected Diff is =" + expDiff);
		for (DAOAttendanceInfo userAttenInfo : userAttendance.findByUserIdOrderByDateDesc(userId)) {
			DAOAttendanceInfo dateWiseattend = userAttenInfo;
			Date actIn = dateWiseattend.getActualIn();
			Date actOut = dateWiseattend.getActualOut();
			Map<String, Object> dailyAtten = new HashMap<String, Object>();
			Date date = dateWiseattend.getDate();
			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm"); // 24 hrs HH formatter
			DateFormat format2 = new SimpleDateFormat("EEEE");
			String dayOfWeek = format2.format(date);
			Date todayDate = new Date();
			String todayDinStr = format1.format(todayDate);
			if (!(todayDinStr).equals(format1.format(date))) {
				if (dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday")) {
					userAttenInfo.setStatus("WO");
					userAttendance.save(userAttenInfo);
					dailyAtten.put("ShiftId", shiftId);
					dailyAtten.put("Date", date);
					dailyAtten.put("Shift",
							"Day(" + timeFormatter.format(expIn) + " To " + timeFormatter.format(expOut) + ")");
					dailyAtten.put("Expected-In", "00:00");
					dailyAtten.put("Actual-In", "00:00");
					dailyAtten.put("Actual-Out", "00:00");
					dailyAtten.put("Expected-Out", "00:00");
					dailyAtten.put("Work Hours", "NA");
					dailyAtten.put("Status", userAttenInfo.getStatus());
					attenList.add(dailyAtten);
				} else {
					if (actOut == null) {
						userAttenInfo.setStatus("MIS");
						userAttendance.save(userAttenInfo);
						dailyAtten.put("ShiftId", shiftId);
						dailyAtten.put("Date", date);
						dailyAtten.put("Shift",
								"Day(" + timeFormatter.format(expIn) + " To " + timeFormatter.format(expOut) + ")");
						dailyAtten.put("Expected-In", timeFormatter.format(expIn));
						dailyAtten.put("Actual-In", timeFormatter.format(actIn));
						dailyAtten.put("Actual-Out","NA");
						dailyAtten.put("Expected-Out", timeFormatter.format(expOut));
						dailyAtten.put("Work Hours","NA");
						dailyAtten.put("Status", userAttenInfo.getStatus());
						attenList.add(dailyAtten);

					} else {
						long actualDiff = calculateTimeDiff(actIn, actOut);
						System.out.println("Actual Diff is =" + actualDiff);
						String precDiffInHr = (actualDiff / 60) > 9 ? Long.toString(actualDiff / 60)
								: "0" + Long.toString(actualDiff / 60);
						String precDiffInMin = (actualDiff % 60) > 9 ? Long.toString(actualDiff % 60)
								: "0" + Long.toString(actualDiff % 60);
						if (actualDiff != 0 && ((actualDiff < expDiff)
								|| (actualDiff >= expDiff && Math.abs(calculateTimeDiff(expIn, actIn)) > 120))) {
							userAttenInfo.setStatus("HD");
							userAttendance.save(userAttenInfo);
							dailyAtten.put("ShiftId", shiftId);
							dailyAtten.put("Date", date);
							dailyAtten.put("Shift",
									"Day(" + timeFormatter.format(expIn) + " To " + timeFormatter.format(expOut) + ")");
							dailyAtten.put("Expected-In", timeFormatter.format(expIn));
							dailyAtten.put("Actual-In", timeFormatter.format(actIn));
							dailyAtten.put("Actual-Out", timeFormatter.format(actOut));
							dailyAtten.put("Expected-Out", timeFormatter.format(expOut));
							dailyAtten.put("Work Hours", precDiffInHr + ":" + precDiffInMin);
							dailyAtten.put("Status", userAttenInfo.getStatus());
							attenList.add(dailyAtten);

						} else {
							if (actualDiff >= expDiff) {
								userAttenInfo.setStatus("P");
								userAttendance.save(userAttenInfo);
								dailyAtten.put("ShiftId", shiftId);
								dailyAtten.put("Date", date);
								dailyAtten.put("Shift", "Day(" + timeFormatter.format(expIn) + " To "
										+ timeFormatter.format(expOut) + ")");
								dailyAtten.put("Expected-In", expIn);
								dailyAtten.put("Expected-In", timeFormatter.format(expIn));
								dailyAtten.put("Actual-In", timeFormatter.format(actIn));
								dailyAtten.put("Actual-Out", timeFormatter.format(actOut));
								dailyAtten.put("Expected-Out", timeFormatter.format(expOut));
								dailyAtten.put("Work Hours", precDiffInHr + ":" + precDiffInMin);
								dailyAtten.put("Status", userAttenInfo.getStatus());
								attenList.add(dailyAtten);
							} else {
								if (actualDiff == 0) {
									userAttenInfo.setStatus("A");
									userAttendance.save(userAttenInfo);
									dailyAtten.put("ShiftId", shiftId);
									dailyAtten.put("Date", date);
									dailyAtten.put("Shift", "Day(" + timeFormatter.format(expIn) + " To "
											+ timeFormatter.format(expOut) + ")");
									dailyAtten.put("Expected-In", expIn);
									dailyAtten.put("Expected-In", timeFormatter.format(expIn));
									dailyAtten.put("Actual-In", timeFormatter.format(actIn));
									dailyAtten.put("Actual-Out", timeFormatter.format(actOut));
									dailyAtten.put("Expected-Out", timeFormatter.format(expOut));
									dailyAtten.put("Work Hours", "00" + ":" + "00");
									dailyAtten.put("Status", userAttenInfo.getStatus());
									attenList.add(dailyAtten);
								}
							}
						}
					}
				}
			}
		}
		return attenList;
	}

	private java.sql.Date getCurrentDate() {
		// TODO Auto-generated method stub
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date currDate = new Date();
//		String curr = formatter.format(currDate);

		long millis = System.currentTimeMillis();
		// creating a new object of the class Date
		java.sql.Date date = new java.sql.Date(millis);
		return date;
	}

	private long calculateTimeDiff(Date time1, Date time2) {

		String time1InString = time1.toString();
		String time2InString = time2.toString();
		LocalTime t1 = LocalTime.parse(time1InString);
		LocalTime t2 = LocalTime.parse(time2InString);
		Duration diff = Duration.between(t1, t2);

		return diff.toMinutes();
	}

	public List<Map<String, Object>> getPunchInfo() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> punch = new ArrayList<Map<String, Object>>();
		DAOAttendanceInfo latestPunch = userAttendance.findByUserIdAndDate(getUserDetails().getUserId(),
				getCurrentDate());
		Map<String, Object> putPunch = new HashMap<String, Object>();
		if (latestPunch == null) {
			putPunch.put("PunchIn", null);
			putPunch.put("PunchOut", null);
			punch.add(putPunch);
			return punch;
		}
		putPunch.put("PunchIn", latestPunch.getActualIn());
		putPunch.put("PunchOut", latestPunch.getActualOut());
		punch.add(putPunch);
		return punch;

		
	}

}
