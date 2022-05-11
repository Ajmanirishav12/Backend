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
@Table(name = "leave_info")
public class DAOleaveInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_info_id")
	Integer leaveInfoId;
	@Column(name = "user_id")
	Integer userId;
	@Column(name = "emp_code")
	String empCode;
	@Column(name = "leave_type")
	String leaveType;
	@Column(name = "leave_reason")
	String leaveReason;
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	Date endDate;
	@Column(name = "classpath")
	String classPath;
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	Date createdDate;
	@Column(name = "modified_date")
	@Temporal(TemporalType.DATE)
	Date modifiedDate;
	@Column(name = "created_by")
	Long createdBy;
	@Column(name = "modified_by")
	Long modifiedBy;
	@Column(name = "leave_status")
	String leaveStatus;
	@Column(name = "org_id")
	Integer orgId;

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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

}
