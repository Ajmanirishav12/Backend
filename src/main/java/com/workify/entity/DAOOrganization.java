package com.workify.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "organization")
public class DAOOrganization {


	@Id
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "org_name")
	private String orgName;

	@Column(name = "is_active")
	private boolean active;

	@Column(name = "org_logo")
	private String orgLogo;

	@Column(name = "org_code")
	private String orgCode;
	
	@Column(name = "login_banner_img")
	private String loginBannerImg;

	@Column(name = "theme")
	private String theme;

	@Column(name = "cl_count")
	private Integer clCount;

	@Column(name = "pl_count")
	private Integer plCount;

	@Column(name = "portal_url")
	private String portalUrl;

	@Column(name = "session_timeout")
	private String sessionTimeOut;
	
	@Column(name = "industry")
	private String industry;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "contract_start_date")
	@Temporal(TemporalType.DATE)
	private Date contractStartDate;
	
	
	@Column(name = "contract_end_date")
	@Temporal(TemporalType.DATE)
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
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
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
