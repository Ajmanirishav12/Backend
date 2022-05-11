package com.workify.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workify.entity.DAOleaveInfo;
import com.workify.input.LeaveIdClass;
import com.workify.model.LeaveInfoTO;
import com.workify.model.UserLeaveBalanceTO;
import com.workify.output.LeavesForManagers;
import com.workify.service.LeaveService;

@RestController
@RequestMapping("/workify/v1/leave")
@CrossOrigin(origins = "*")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public int saveLeave(@RequestBody LeaveInfoTO leave) throws Exception{
		return leaveService.saveLeaveInfo(leave);
	}
	
	@RequestMapping(value = "/balance", method = RequestMethod.POST)
	public String saveUserLeaveBalance(@RequestBody UserLeaveBalanceTO leaveBalance) throws Exception{
		ResponseEntity.ok(leaveService.saveUserLeaveBalance(leaveBalance));
		return "leaveBalance added!";
	}
	
	//for L1managers
	@RequestMapping(value = "/getleaves")
	public List<LeavesForManagers> getAppliedLeaves(Authentication authentication) {
		List<LeavesForManagers> leaves = leaveService.getLeaves(authentication);
		return leaves;
	}
	
	//for logged in users
	@RequestMapping(value = "/yourleaves")
	public ResponseEntity<List<Map<String, Object>>> getUserLeaves(Authentication authentication) throws ParseException{
		List<Map<String, Object>> UserLeaves = leaveService.getYourLeaves(authentication);
		if(UserLeaves.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(UserLeaves));
	}
	
	@RequestMapping(value = "/yourbalanceleaves")
	public ResponseEntity<Map<String,Object>> getUserLeavesBalance(Authentication authentication){
		Map<String,Object> balance = leaveService.getYourLeaveBalance(authentication);
		if(balance.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(balance));
	}
	
	@RequestMapping(value = "/approveleave")
	public String leaveApprove(@RequestBody LeaveIdClass leaveIdclass) {
		Integer leaveId = leaveIdclass.getLeaveId();
		System.out.println("abc");
		return leaveService.approveLeave(leaveId);
	}
	
	@RequestMapping(value = "/rejectleave")
	public String leaveReject(@RequestBody LeaveIdClass leaveIdclass) {
		Integer leaveId = leaveIdclass.getLeaveId();
		return leaveService.rejectLeave(leaveId);
	}
	
}

