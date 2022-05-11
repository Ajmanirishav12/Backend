package com.workify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workify.entity.DAOOrganization;
import com.workify.model.OrganizationTO;
import com.workify.repository.OrganizationRepository;

@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository orgRepo;
	
	
	public DAOOrganization saveOrgDetails(OrganizationTO organization) {
		
		DAOOrganization org  = new DAOOrganization();
		org.setOrgId(organization.getOrgId());
		org.setOrgName(organization.getOrgName());
		org.setActive(organization.isActive());
		org.setOrgLogo(organization.getOrgLogo());
		org.setOrgCode(organization.getOrgCode());
		org.setLoginBannerImg(organization.getLoginBannerImg());
		org.setTheme(organization.getTheme());
		org.setClCount(organization.getClCount());
		org.setPlCount(organization.getPlCount());
		org.setPortalUrl(organization.getPortalUrl());
		org.setSessionTimeOut(organization.getSessionTimeOut());
		org.setIndustry(organization.getIndustry());
		org.setAddress(organization.getAddress());
		org.setContractStartDate(organization.getContractStartDate());
		org.setContractEndDate(organization.getContractEndDate());	
		return orgRepo.save(org);
	}

	


	
}
