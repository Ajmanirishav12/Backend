package com.workify.model;

import java.util.Date;



public class OrganizationTO {

	private Integer orgId;
	
	private String orgName;

	private boolean isActive;

	private String orgLogo;

	private String orgCode;
	
	private String loginBannerImg;

	private String theme;

	private Integer clCount;

	private Integer plCount;

	private String portalUrl;

	private String sessionTimeOut;
	
	private String industry;
	
	private String address;
	
	private Date contractStartDate;
	
	private Date contractEndDate;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	public String getOrgLogo() {
		return orgLogo;
	}

	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getLoginBannerImg() {
		return loginBannerImg;
	}

	public void setLoginBannerImg(String loginBannerImg) {
		this.loginBannerImg = loginBannerImg;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Integer getClCount() {
		return clCount;
	}

	public void setClCount(Integer clCount) {
		this.clCount = clCount;
	}

	public Integer getPlCount() {
		return plCount;
	}

	public void setPlCount(Integer plCount) {
		this.plCount = plCount;
	}

	public String getPortalUrl() {
		return portalUrl;
	}

	public void setPortalUrl(String portalUrl) {
		this.portalUrl = portalUrl;
	}

	public String getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(String sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	
}
