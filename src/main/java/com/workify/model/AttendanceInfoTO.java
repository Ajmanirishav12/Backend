package com.workify.model;

import java.sql.Time;
import java.util.Date;


public class AttendanceInfoTO {

	private Integer attendanceId;

	private Integer userId;

	private String locationInfo;
	
	private String empCode;

	private String status;

	private Date date;

	private Time actualIn;

	private Time actualOut;

	private Date creationDate;

	private Date modifiedDate;

	private Integer createdBy;

	private Integer modifiedBy;

	

	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}
	

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getStatus() {
		return status;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date currDate) {
		this.date = currDate;
	}
	

	public Time getActualIn() {
		return actualIn;
	}

	public void setActualIn(Time actualIn) {
		this.actualIn = actualIn;
	}

	public Time getActualOut() {
		return actualOut;
	}

	public void setActualOut(Time actualOut) {
		this.actualOut = actualOut;
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
