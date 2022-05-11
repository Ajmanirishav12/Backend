package com.workify.model;

import java.sql.Time;
import java.util.Date;

public class UserShiftDetailsTO {

	private Integer userShiftId;

	private Integer attendanceId;

	private Integer orgId;

	private Integer userId;

	private String empCode;

	private Time expIn;

	private Time expOut;

	private Date creationDate;

	private Date modifiedDate;

	private Integer createdBy;

	private Integer modifiedBy;

	public Integer getUserShiftId() {
		return userShiftId;
	}

	public void setUserShiftId(Integer userShiftId) {
		this.userShiftId = userShiftId;
	}

	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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

	public Time getExpIn() {
		return expIn;
	}

	public void setExpIn(Time expIn) {
		this.expIn = expIn;
	}

	public Time getExpOut() {
		return expOut;
	}

	public void setExpOut(Time expOut) {
		this.expOut = expOut;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
