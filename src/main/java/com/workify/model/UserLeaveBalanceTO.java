package com.workify.model;

import java.util.Date;

public class UserLeaveBalanceTO {
	Integer leaveInfoId;
	Integer userId;
	Long leaveBalanceId;
	Long orgId;
	Integer clCount;
	Integer plCount;
	Date creationDate;
	Date modifiedDate;
	Long createdBy;
	Long modifiedBy;
	public Integer getLeaveInfoId() {
		return leaveInfoId;
	}
	public void setLeaveInfoId(Integer leaveInfoId) {
		this.leaveInfoId = leaveInfoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Long getLeaveBalanceId() {
		return leaveBalanceId;
	}
	public void setLeaveBalanceId(Long leaveBalanceId) {
		this.leaveBalanceId = leaveBalanceId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
}
