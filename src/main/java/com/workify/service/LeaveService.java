
package com.workify.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.workify.entity.DAOUser;
import com.workify.entity.DAOUserLeaveBalance;
import com.workify.entity.DAOUserPositionInfo;
import com.workify.entity.DAOleaveInfo;
import com.workify.model.LeaveInfoTO;
import com.workify.model.UserLeaveBalanceTO;
import com.workify.output.LeavesForManagers;
import com.workify.repository.LeaveRepository;
import com.workify.repository.UserLeaveBalanceRepository;
import com.workify.repository.UserPositionInfoRepository;
import com.workify.repository.UserRepository;

@Service
public class LeaveService {
	@Autowired
	private LeaveRepository leaveDao;
	@Autowired
	private UserLeaveBalanceRepository userLeaveBalanceRepository;
	@Autowired
	private UserRepository userDao;
	@Autowired
	private UserPositionInfoRepository userPositionInfoRepository;
	
	private static long noOfDaysBetween;

	public int saveLeaveInfo(LeaveInfoTO leave) {
		leave.setLeaveStatus("Pending");
		Date beginDate = leave.getStartDate();
		Date endingDate = leave.getEndDate();
		LeaveService.noOfDaysBetween = ChronoUnit.DAYS.between(beginDate.toInstant(), endingDate.toInstant());
		LeaveService.noOfDaysBetween++;
		if(LeaveService.noOfDaysBetween <= 0) {
			return -11;
			//leave endDate is before startDate 
		}
		String appLeaveType = leave.getLeaveType();
		Integer appUserId = leave.getUserId();
		DAOUserLeaveBalance dlb = userLeaveBalanceRepository.findByUserId(appUserId);
		if (appLeaveType.equals("PL") && dlb.getPlCount() < LeaveService.noOfDaysBetween || appLeaveType.equals("CL") && dlb.getClCount() < LeaveService.noOfDaysBetween) {
			//return "Sorry, leave quota exhaused!";
			return -5;
		}
		if(appLeaveType.equals("Birthday")) {
			for(DAOleaveInfo dupleave : leaveDao.findAll()) {
				if(dupleave.getUserId().equals(appUserId)) {
					if(dupleave.getLeaveStatus().equals("Approved") && dupleave.getLeaveType().equals("Birthday")) {
						return -7;
						//bday leave already approved.
					}
					if(dupleave.getLeaveStatus().equals("Pending") && dupleave.getLeaveType().equals("Birthday")) {
						return -8;
						//bday leave already in pending.
					}
				}
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date currDate = leave.getStartDate();
			Date endDate = leave.getEndDate();
			String currEnd = formatter.format(endDate);
			String curr = formatter.format(currDate);
			if(!curr.equals(currEnd)) {
				//return "Sorry, you can apply b'day leave for only one day!";
				return -2;
			}
			String currDay = curr.substring(0,2);
			String currMonth = curr.substring(3,5);
			Optional<DAOUser> user = userDao.findById(appUserId);
			if(user.isPresent()) {
				DAOUser currUser = user.get();
				Date currUserDob = currUser.getDob();
				String currDob = formatter.format(currUserDob);
				String currDobDay = currDob.substring(0,2);
				String currDobMonth = currDob.substring(3, 5);
				if(!currDay.equals(currDobDay) || !currMonth.equals(currDobMonth)) {
					//return "Sorry, it's not your b'day!";
					return -1;
				}
			}
		}
		if(appLeaveType.equals("Anniversary")) {
			for(DAOleaveInfo dupleave : leaveDao.findAll()) {
				if(dupleave.getUserId().equals(appUserId) && dupleave.getLeaveType().equals("Anniversary")) {
					if(dupleave.getLeaveStatus().equals("Approved")) {
						return -9;
						//anniversary leave already approved.
					}
					if(dupleave.getLeaveStatus().equals("Pending") && dupleave.getLeaveType().equals("Anniversary")) {
						return -10;
						//anniversary leave already in pending.
					}
				}
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date appliedLeaveStartDate = leave.getStartDate();
			Date appliedLeaveEndDate = leave.getEndDate();
			String currStartDate = formatter.format(appliedLeaveStartDate);
			String currEndDate = formatter.format(appliedLeaveEndDate);
			if(!currStartDate.equals(currEndDate)) {
				//return "Sorry, you can apply anniversary leave for one day only!";
				return -3;
			}
			String currDay = currStartDate.substring(0,2);
			String currMonth = currEndDate.substring(3,5);
			Optional<DAOUser> user = userDao.findById(appUserId);
			if(user.isPresent()) {
				DAOUser currUser = user.get();
				if(!currUser.getMarriageStatus().equals("Married")) {
					//return "Sorry, you are not eligible for anniversary leave!";
					return -6;
				}
				Date currUserDom = currUser.getDom();
				String currDom = formatter.format(currUserDom);
				String currDomDay = currDom.substring(0, 2);
				String currDomMonth = currDom.substring(3, 5);
				if(!currDay.equals(currDomDay) || !currMonth.equals(currDomMonth)) {
					//return "Sorry, it's not your anniversary!";
					return -4;
				}
			}
		}
			
			
		DAOleaveInfo newLeave = new DAOleaveInfo();
		newLeave.setLeaveInfoId(leave.getLeaveInfoId());
		newLeave.setUserId(leave.getUserId());
		newLeave.setEmpCode(leave.getEmpCode());
		newLeave.setLeaveType(leave.getLeaveType());
		newLeave.setLeaveReason(leave.getLeaveReason());
		newLeave.setStartDate(leave.getStartDate());
		newLeave.setEndDate(leave.getEndDate());
		newLeave.setClassPath(leave.getClassPath());
		newLeave.setCreatedDate(leave.getCreatedDate());
		newLeave.setModifiedDate(leave.getModifiedDate());
		newLeave.setCreatedBy(leave.getCreatedBy());
		newLeave.setModifiedBy(leave.getModifiedBy());
		newLeave.setLeaveStatus(leave.getLeaveStatus());
		newLeave.setOrgId(leave.getOrgId());
		leaveDao.save(newLeave);
		Integer days = (int) LeaveService.noOfDaysBetween;
		if (leave.getLeaveType().equals("PL")) {
			Integer plc = dlb.getPlCount() - days;
			dlb.setPlCount(plc);
		}
		if (leave.getLeaveType().equals("CL")) {
			Integer clc = dlb.getClCount() - days;
			dlb.setClCount(clc);
		}
		userLeaveBalanceRepository.save(dlb);
		//return "Leave Applied!";
		return 1;
	}

	public DAOUserLeaveBalance saveUserLeaveBalance(UserLeaveBalanceTO leaveBalance) {
		DAOUserLeaveBalance newLeaveBal = new DAOUserLeaveBalance();
		newLeaveBal.setLeaveInfoId(leaveBalance.getLeaveInfoId());
		newLeaveBal.setUserId(leaveBalance.getUserId());
		newLeaveBal.setLeaveBalanceId(leaveBalance.getLeaveBalanceId());
		newLeaveBal.setOrgId(leaveBalance.getOrgId());
		newLeaveBal.setClCount(leaveBalance.getClCount());
		newLeaveBal.setPlCount(leaveBalance.getPlCount());
		newLeaveBal.setCreationDate(leaveBalance.getCreationDate());
		newLeaveBal.setModifiedDate(leaveBalance.getModifiedDate());
		newLeaveBal.setCreatedBy(leaveBalance.getCreatedBy());
		newLeaveBal.setModifiedBy(leaveBalance.getModifiedBy());
		return userLeaveBalanceRepository.save(newLeaveBal);
	}

	public List<LeavesForManagers> getLeaves(Authentication authentication) {
		String currUser = authentication.getName();
		DAOUser l1user = userDao.findByUsername(currUser);
		Integer l1userID = l1user.getUserId();
		Set<Integer> usersUnderL1 = new HashSet<>();
		for (DAOUserPositionInfo userUnder : userPositionInfoRepository.findAll()) {
			if (userUnder.getL1ManagerId().equals(l1userID)) {
				usersUnderL1.add(userUnder.getUserId());
			}
		}
		List<LeavesForManagers> leaves = new ArrayList<>();
		for (DAOleaveInfo leave : leaveDao.findAll()) {
			Integer currUserId = leave.getUserId();
			if (usersUnderL1.contains(currUserId)) {
				Optional<DAOUser> currUserDetails = userDao.findById(currUserId);
				String name = "";
				if(currUserDetails.isPresent()) {
					DAOUser userDetails = currUserDetails.get();
					name = userDetails.getFullName();
				}
				LeavesForManagers lfm = new LeavesForManagers();
				lfm.setLeaveInfoId(leave.getLeaveInfoId());
				lfm.setUserId(leave.getUserId());
				lfm.setUserName(name);
				lfm.setEmpCode(leave.getEmpCode());
				lfm.setLeaveType(leave.getLeaveType());
				lfm.setLeaveReason(lfm.getLeaveReason());
				lfm.setStartDate(leave.getStartDate());
				lfm.setEndDate(leave.getEndDate());
				lfm.setClassPath(leave.getClassPath());
				lfm.setCreatedDate(leave.getCreatedDate());
				lfm.setModifiedDate(leave.getModifiedDate());
				lfm.setCreatedBy(leave.getCreatedBy());
				lfm.setModifiedBy(lfm.getModifiedBy());
				lfm.setLeaveStatus(leave.getLeaveStatus());
				lfm.setOrgId(leave.getOrgId());
				leaves.add(lfm);
			}
		}
		return leaves;
	}

	public List<Map<String, Object>> getYourLeaves(Authentication authentication) throws ParseException {
		String currUserName = authentication.getName();
		List<Map<String, Object>> yourLeaves = new ArrayList<>();
		DAOUser currUser = userDao.findByUsername(currUserName);
		Date currDate = new Date();
		System.out.println(currDate);
		for (DAOleaveInfo leave : leaveDao.findAll()) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strleaveDate = formatter.format(leave.getEndDate());
			String LeavecurrDay = strleaveDate.substring(0, 2);
			String LeavecurrMonth = strleaveDate.substring(3, 5);
			String LeavecurrYear = strleaveDate.substring(6,10);
			Date leaveDate = formatter.parse(strleaveDate);
			if (leave.getUserId().equals(currUser.getUserId())) {
				String curr = formatter.format(currDate);
				String currDay = curr.substring(0, 2);
				String currMonth = curr.substring(3, 5);
				String currYear = curr.substring(6,10);
				if((currDay.equals(LeavecurrDay)) && (currMonth.equals(LeavecurrMonth)) && (currYear.equals(LeavecurrYear))) {
					Map<String, Object> currLeave = new HashMap<>();
					currLeave.put("leaveType", leave.getLeaveType());
					currLeave.put("startDate", leave.getStartDate());
					currLeave.put("endDate", leave.getEndDate());
					currLeave.put("leaveStatus", leave.getLeaveStatus());
					yourLeaves.add(currLeave);
				}
				if(leaveDate.after(currDate)) {
					Map<String, Object> currLeave = new HashMap<>();
					currLeave.put("leaveType", leave.getLeaveType());
					currLeave.put("startDate", leave.getStartDate());
					currLeave.put("endDate", leave.getEndDate());
					currLeave.put("leaveStatus", leave.getLeaveStatus());
					yourLeaves.add(currLeave);
				}
			}
		}
		return yourLeaves;
	}

	public Map<String, Object> getYourLeaveBalance(Authentication authentication) {
		String currUserName = authentication.getName();
		DAOUser currUser = userDao.findByUsername(currUserName);
		Integer currUserId = currUser.getUserId();
		Map<String, Object> balanceLeaves = new HashMap<>();
		DAOUserLeaveBalance balanceInfo = userLeaveBalanceRepository.findByUserId(currUserId);
		balanceLeaves.put("PL left", balanceInfo.getPlCount());
		balanceLeaves.put("CL left", balanceInfo.getClCount());
		return balanceLeaves;
	}

	public String approveLeave(Integer leaveId) {
		Optional<DAOleaveInfo> leaveInfo = leaveDao.findById(leaveId);
		if (leaveInfo.isPresent()) {
			DAOleaveInfo currLeaveInfo = leaveInfo.get();
			currLeaveInfo.setLeaveStatus("Approved");
			leaveDao.save(currLeaveInfo);
			return "Leave Approved!";
		}
		return "Leave ID is incorrect!";
	}

	public String rejectLeave(Integer leaveId) {
		Optional<DAOleaveInfo> leaveInfo = leaveDao.findById(leaveId);
		if (leaveInfo.isPresent()) {
			DAOleaveInfo currLeaveInfo = leaveInfo.get();
			currLeaveInfo.setLeaveStatus("Rejected");
			leaveDao.save(currLeaveInfo);
			Integer userId = currLeaveInfo.getUserId();
			DAOUserLeaveBalance dlb = userLeaveBalanceRepository.findByUserId(userId);
			Integer days = (int) LeaveService.noOfDaysBetween;
			if(currLeaveInfo.getLeaveType().equals("PL")) {
				Integer plc = dlb.getPlCount();
				plc = plc + days;
				dlb.setPlCount(plc);
			}
			if(currLeaveInfo.getLeaveType().equals("CL")) {
				Integer clc = dlb.getClCount();
				clc = clc + days;
				dlb.setClCount(clc);
			}
			userLeaveBalanceRepository.save(dlb);
			return "Leave Rejected!";
		}
		return "Leave ID is incorrect!";
	}
}