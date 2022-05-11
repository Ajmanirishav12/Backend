package com.workify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workify.entity.DAOUserPositionInfo;
import com.workify.model.UserPositionInfoTO;
import com.workify.repository.UserPositionInfoRepository;

@Service
public class UserPositionInfoService {
	@Autowired
	private UserPositionInfoRepository userPositionInfoRepository;
	
	public DAOUserPositionInfo saveUserPositionInfo(UserPositionInfoTO userPositionInfoTO) {
		DAOUserPositionInfo newUserpositionInfo = new DAOUserPositionInfo();
		newUserpositionInfo.setUserPosId(userPositionInfoTO.getUserPosId());
		newUserpositionInfo.setUserId(userPositionInfoTO.getUserId());
		newUserpositionInfo.setEmpCode(userPositionInfoTO.getEmpCode());
		newUserpositionInfo.setDesignation(userPositionInfoTO.getDesignation());
		newUserpositionInfo.setGrade(userPositionInfoTO.getGrade());
		newUserpositionInfo.setEmpCategory(userPositionInfoTO.getEmpCategory());
		newUserpositionInfo.setEmpStatus(userPositionInfoTO.getEmpStatus());
		newUserpositionInfo.setEmpType(userPositionInfoTO.getEmpType());
		newUserpositionInfo.setDepartment(userPositionInfoTO.getDepartment());
		newUserpositionInfo.setLocation(userPositionInfoTO.getLocation());
		newUserpositionInfo.setL1ManagerId(userPositionInfoTO.getL1ManagerId());
		newUserpositionInfo.setL2ManagerId(userPositionInfoTO.getL2ManagerId());
		newUserpositionInfo.setHrManagerId(userPositionInfoTO.getHrManagerId());
		newUserpositionInfo.setOrgId(userPositionInfoTO.getOrgId());
		newUserpositionInfo.setIsActive(userPositionInfoTO.getIsActive());
		newUserpositionInfo.setCreationDate(userPositionInfoTO.getCreationDate());
		newUserpositionInfo.setModifiedDate(userPositionInfoTO.getModifiedDate());
		newUserpositionInfo.setCreatedBy(userPositionInfoTO.getCreatedBy());
		newUserpositionInfo.setModifiedBy(userPositionInfoTO.getModifiedBy());
		return userPositionInfoRepository.save(newUserpositionInfo);
	}
}
