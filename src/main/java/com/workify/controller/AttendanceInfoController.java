package com.workify.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workify.model.AttendanceInfoTO;
import com.workify.model.UserShiftDetailsTO;
import com.workify.service.AttendanceInfoService;

@RestController
@RequestMapping("/workify/v1/attendance")
@CrossOrigin(origins = "*")
public class AttendanceInfoController {

	@Autowired
	private AttendanceInfoService attendanceInfoService;

	@RequestMapping(value = "/getpunch")
	public List<Map<String, Object>> getPunch() {
		
		
		return attendanceInfoService.getPunchInfo();

	}

	@RequestMapping(value = "/punchin")
	public String applyAttendance(@RequestBody AttendanceInfoTO attendanceInfo) throws Exception {

		if (attendanceInfoService.saveAttendance(attendanceInfo)) {
			return "Punched-In Success";
		}
		return "Multiple Punch-In Not Allowed";
	}

	@RequestMapping(value = "/usershiftdetails")
	public String applyAttendance(@RequestBody UserShiftDetailsTO userShiftDetails) throws Exception {
		ResponseEntity.ok(attendanceInfoService.saveUserShift(userShiftDetails));
		return "User Shift Details Successfully!";
	}

	@RequestMapping(value = "/attendancelist")
	public List<Map<String, Object>> getAttendanceList() throws Exception {
		List<Map<String, Object>> attendanceList = attendanceInfoService.getAttendanceDetails();
		return attendanceList;
	}

	@RequestMapping(value = "/punchout")
	public String updatePunchOut(@RequestBody AttendanceInfoTO punchOutUpdate) throws Exception {

		return attendanceInfoService.updateUserPunch(punchOutUpdate);

	}

}
