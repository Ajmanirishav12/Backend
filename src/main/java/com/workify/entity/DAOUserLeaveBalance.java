package com.workify.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_leave_balance")
public class DAOUserLeaveBalance {
	@Column(name = "leave_info_id")
	Integer leaveInfoId;
	@Column(name = "user_id")
	Integer userId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_balance_id")
	Long leaveBalanceId;
	@Column(name = "org_id")
	Long orgId;
	@Column(name = "cl_count")
	Integer clCount;
	@Column(name = "pl_count")
	Integer plCount;
	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	Date creationDate;
	@Column(name = "modified_date")
	@Temporal(TemporalType.DATE)
	Date modifiedDate;
	@Column(name = "created_by")
	Long createdBy;
	@Column(name = "modified_by")
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
