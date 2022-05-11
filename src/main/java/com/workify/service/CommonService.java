package com.workify.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.workify.entity.DAOUser;
import com.workify.entity.DAOUserPositionInfo;
import com.workify.input.UserDetailsInput;
import com.workify.output.UserDetailsOutput;
import com.workify.repository.UserPositionInfoRepository;
import com.workify.repository.UserRepository;

@Service
public class CommonService {
	
	@Autowired
	UserRepository userDao;
	
	@Autowired
	UserPositionInfoRepository userPositionInfoRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public Map<String, Object> getLoggedinUser(Authentication authentication)
	{
		Map<String, Object> res = new HashMap<>();
		String currUser = authentication.getName();
		DAOUser user = userDao.findByUsername(currUser);
		res.put("firstName", user.getFirstName());
		res.put("lastName", user.getLastName());
		res.put("middleName", user.getMiddleName());
		res.put("fullName", user.getFullName());
		res.put("empCode", user.getEmpCode());
		res.put("role", user.getRole());
		res.put("userId", user.getUserId());
		return res;
	}
	
	public UserDetailsOutput getLoggedinUserProfile(Authentication authentication)
	{
		
		String currUser = authentication.getName();
		DAOUser user = userDao.findByUsername(currUser);
		DAOUserPositionInfo userpos = userPositionInfoRepository.findByUserId(user.getUserId());
		UserDetailsOutput userDetailsOutput = new UserDetailsOutput();
		userDetailsOutput.setUserId(user.getUserId());
		userDetailsOutput.setCity(user.getCity());
		userDetailsOutput.setCountry(user.getCountry());
		userDetailsOutput.setDepartment(user.getDepartment());
		userDetailsOutput.setDob(user.getDob());
		userDetailsOutput.setDoj(user.getDoj());
		userDetailsOutput.setEmpCode(user.getEmpCode());
		userDetailsOutput.setFirstName(user.getFirstName());
		userDetailsOutput.setMiddleName(user.getMiddleName());
		userDetailsOutput.setLastName(user.getLastName());
		userDetailsOutput.setFullName(user.getFullName());
		userDetailsOutput.setAccountLocked(user.isAccountLocked());
		userDetailsOutput.setActive(user.isActive());
		userDetailsOutput.setMobile(user.getMobile());
		userDetailsOutput.setModifiedBy(user.getModifiedBy());
		userDetailsOutput.setCreatedBy(user.getCreatedBy());
		userDetailsOutput.setModifiedDate(user.getModifiedDate());
		userDetailsOutput.setCreatedDate(user.getCreationDate());
		userDetailsOutput.setOfficialMail(user.getOfficialMail());
		userDetailsOutput.setOrgId(user.getOrgId());
		userDetailsOutput.setUserName(user.getUsername());
		userDetailsOutput.setPassword(user.getPassword());
		userDetailsOutput.setJobRole(user.getRole());
		userDetailsOutput.setState(user.getState());
		userDetailsOutput.setWorkLocation(user.getWorkLocation());
		userDetailsOutput.setMarriageStatus(user.getMarriageStatus());
		userDetailsOutput.setDom(user.getDom());
		userDetailsOutput.setJobPosition(user.getJobPosition());
		userDetailsOutput.setDesignation(userpos.getDesignation());
		userDetailsOutput.setGrade(userpos.getGrade());
		userDetailsOutput.setEmployementCategory(userpos.getEmpCategory());
		userDetailsOutput.setEmployementStatus(userpos.getEmpStatus());
		userDetailsOutput.setEmployementType(userpos.getEmpType());
		userDetailsOutput.setL1ManagerId(userpos.getL1ManagerId());
		userDetailsOutput.setL2ManagerId(userpos.getL2ManagerId());
		userDetailsOutput.setHrManagerId(userpos.getHrManagerId());
		return userDetailsOutput;
	}
	
//	public DAOUserPositionInfo getLoggedinUserPositionInfo(Authentication authentication) {
//		String currUser = authentication.getName();
//		DAOUser user = userDao.findByUsername(currUser);
//		Integer userID = user.getUserId();
//		DAOUserPositionInfo userPosInfo = userPositionInfoRepository.findByUserId(userID);
//		return userPosInfo;
//	}
	
	public List<UserDetailsOutput> getAllEmployees(){
		//List<DAOUser> allEmployees = new ArrayList<>();
		List<UserDetailsOutput> allEmployees = new ArrayList<>();
		for(DAOUser currUser : userDao.findAll()) {
			UserDetailsOutput userDetailsOutput = new UserDetailsOutput();
			userDetailsOutput.setUserId(currUser.getUserId());
			userDetailsOutput.setCity(currUser.getCity());
			userDetailsOutput.setCountry(currUser.getCountry());
			userDetailsOutput.setCreatedBy(currUser.getCreatedBy());
			userDetailsOutput.setDepartment(currUser.getDepartment());
			userDetailsOutput.setDob(currUser.getDob());
			userDetailsOutput.setDoj(currUser.getDoj());
			userDetailsOutput.setEmpCode(currUser.getEmpCode());
			userDetailsOutput.setFirstName(currUser.getFirstName());
			userDetailsOutput.setFullName(currUser.getFullName());
			userDetailsOutput.setAccountLocked(currUser.isAccountLocked());
			userDetailsOutput.setActive(currUser.isActive());
			userDetailsOutput.setLastName(currUser.getLastName());
			userDetailsOutput.setMobile(currUser.getMobile());
			userDetailsOutput.setMiddleName(currUser.getMiddleName());
			userDetailsOutput.setModifiedBy(currUser.getModifiedBy());
			userDetailsOutput.setModifiedDate(currUser.getModifiedDate());
			userDetailsOutput.setCreatedDate(currUser.getCreationDate());
			userDetailsOutput.setOfficialMail(currUser.getOfficialMail());
			userDetailsOutput.setOrgId(currUser.getOrgId());
			userDetailsOutput.setPassword(currUser.getPassword());
			userDetailsOutput.setJobRole(currUser.getRole());
			userDetailsOutput.setState(currUser.getState());
			userDetailsOutput.setUserName(currUser.getUsername());
			userDetailsOutput.setWorkLocation(currUser.getWorkLocation());
			userDetailsOutput.setMarriageStatus(currUser.getMarriageStatus());
			userDetailsOutput.setDom(currUser.getDom());
			userDetailsOutput.setJobPosition(currUser.getJobPosition());
			DAOUserPositionInfo upi = userPositionInfoRepository.findByUserId(currUser.getUserId());
			userDetailsOutput.setDesignation(upi.getDesignation());
			userDetailsOutput.setGrade(upi.getGrade());
			userDetailsOutput.setEmployementCategory(upi.getEmpCategory());
			userDetailsOutput.setEmployementStatus(upi.getEmpStatus());
			userDetailsOutput.setEmployementType(upi.getEmpType());
			userDetailsOutput.setL1ManagerId(upi.getL1ManagerId());
			userDetailsOutput.setL2ManagerId(upi.getL2ManagerId());
			userDetailsOutput.setHrManagerId(upi.getHrManagerId());
			allEmployees.add(userDetailsOutput);
		}
		return allEmployees;
	}
	
	public UserDetailsOutput getUserDetails(Integer userId) {
		Optional<DAOUser> user = userDao.findById(userId);
		if(user.isPresent()) {
			DAOUser currUser = user.get();
			UserDetailsOutput userDetailsOutput = new UserDetailsOutput();
			userDetailsOutput.setUserId(currUser.getUserId());
			userDetailsOutput.setCity(currUser.getCity());
			userDetailsOutput.setCountry(currUser.getCountry());
			userDetailsOutput.setCreatedBy(currUser.getCreatedBy());
			userDetailsOutput.setDepartment(currUser.getDepartment());
			userDetailsOutput.setDob(currUser.getDob());
			userDetailsOutput.setDoj(currUser.getDoj());
			userDetailsOutput.setEmpCode(currUser.getEmpCode());
			userDetailsOutput.setFirstName(currUser.getFirstName());
			userDetailsOutput.setFullName(currUser.getFullName());
			userDetailsOutput.setAccountLocked(currUser.isAccountLocked());
			userDetailsOutput.setActive(currUser.isActive());
			userDetailsOutput.setLastName(currUser.getLastName());
			userDetailsOutput.setMobile(currUser.getMobile());
			userDetailsOutput.setMiddleName(currUser.getMiddleName());
			userDetailsOutput.setModifiedBy(currUser.getModifiedBy());
			userDetailsOutput.setModifiedDate(currUser.getModifiedDate());
			userDetailsOutput.setCreatedDate(currUser.getCreationDate());
			userDetailsOutput.setOfficialMail(currUser.getOfficialMail());
			userDetailsOutput.setOrgId(currUser.getOrgId());
			userDetailsOutput.setPassword(currUser.getPassword());
			userDetailsOutput.setJobRole(currUser.getRole());
			userDetailsOutput.setState(currUser.getState());
			userDetailsOutput.setUserName(currUser.getUsername());
			userDetailsOutput.setWorkLocation(currUser.getWorkLocation());
			userDetailsOutput.setMarriageStatus(currUser.getMarriageStatus());
			userDetailsOutput.setDom(currUser.getDom());
			userDetailsOutput.setJobPosition(currUser.getJobPosition());
			return userDetailsOutput;
		}
		return null;
	}
	
	//update certain user by admin or user
	public String editUserDetails(UserDetailsInput userDetailsInput) {
		Optional<DAOUser> updateUserDetail = userDao.findById(userDetailsInput.getUserId());
		if(!updateUserDetail.isPresent()) return "User Invalid";
		DAOUser updateUserDetails = updateUserDetail.get();
		DAOUserPositionInfo updatePosDetails = userPositionInfoRepository.findByUserId(userDetailsInput.getUserId());
		updateUserDetails.setCity(userDetailsInput.getCity());
		updateUserDetails.setCountry(userDetailsInput.getCountry());
		updateUserDetails.setCreatedBy(userDetailsInput.getCreatedBy());
		updateUserDetails.setCreationDate(userDetailsInput.getCreatedDate());
		updateUserDetails.setDepartment(userDetailsInput.getDepartment());
		updateUserDetails.setDob(userDetailsInput.getDob());
		updateUserDetails.setDoj(userDetailsInput.getDoj());
		updateUserDetails.setEmpCode(userDetailsInput.getEmpCode());
		updateUserDetails.setFirstName(userDetailsInput.getFirstName());
		updateUserDetails.setFullName(userDetailsInput.getFullName());
		updateUserDetails.setAccountLocked(userDetailsInput.isAccountLocked());
		updateUserDetails.setActive(userDetailsInput.isActive());
		updateUserDetails.setLastName(userDetailsInput.getLastName());
		updateUserDetails.setMiddleName(userDetailsInput.getMiddleName());
		updateUserDetails.setMobile(userDetailsInput.getMobile());
		updateUserDetails.setModifiedBy(userDetailsInput.getModifiedBy());
		updateUserDetails.setModifiedDate(userDetailsInput.getModifiedDate());
		updateUserDetails.setOfficialMail(userDetailsInput.getOfficialMail());
		updateUserDetails.setOrgId(userDetailsInput.getOrgId());//encrypt password
		updateUserDetails.setPassword(bcryptEncoder.encode(userDetailsInput.getPassword()));
		updateUserDetails.setRole(userDetailsInput.getJobRole());
		updateUserDetails.setState(userDetailsInput.getState());
		updateUserDetails.setUsername(userDetailsInput.getUserName());
		updateUserDetails.setWorkLocation(userDetailsInput.getWorkLocation());
		updateUserDetails.setMarriageStatus(userDetailsInput.getMarriageStatus());
		updateUserDetails.setDom(userDetailsInput.getDom());
		updateUserDetails.setJobPosition(userDetailsInput.getJobPosition());
		updatePosDetails.setEmpCode(userDetailsInput.getEmpCode());
		updatePosDetails.setDesignation(userDetailsInput.getDesignation());
		updatePosDetails.setGrade(userDetailsInput.getGrade());
		updatePosDetails.setEmpCategory(userDetailsInput.getEmployementCategory());
		updatePosDetails.setEmpStatus(userDetailsInput.getEmployementStatus());
		updatePosDetails.setEmpType(userDetailsInput.getEmployementType());
		updatePosDetails.setDepartment(userDetailsInput.getDepartment());
		updatePosDetails.setLocation(userDetailsInput.getWorkLocation());
		updatePosDetails.setL1ManagerId(userDetailsInput.getL1ManagerId());
		updatePosDetails.setL2ManagerId(userDetailsInput.getL2ManagerId());
		updatePosDetails.setHrManagerId(userDetailsInput.getHrManagerId());
		updatePosDetails.setOrgId(userDetailsInput.getOrgId());
		updatePosDetails.setIsActive(userDetailsInput.isActive());
		updatePosDetails.setCreationDate(userDetailsInput.getCreatedDate());
		updatePosDetails.setModifiedDate(userDetailsInput.getModifiedDate());
		updatePosDetails.setCreatedBy(userDetailsInput.getCreatedBy());
		updatePosDetails.setModifiedBy(userDetailsInput.getModifiedBy());
		userDao.save(updateUserDetails);
		userPositionInfoRepository.save(updatePosDetails);
		return "User updated!";
	}
	
}
