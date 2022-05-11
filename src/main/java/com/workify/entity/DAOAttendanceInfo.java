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
@Table(name = "attendance_info")
public class DAOAttendanceInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_id")
	private Integer attendanceId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "location_info")
	private String locationInfo;
	
	
	
	  @Column(name = "emp_code") private String empCode;
	 
	
	@Column(name = "status")
	private String status;

	@Column(name = "curr_date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "actual_in")
	@Temporal(TemporalType.TIME)
	private java.util.Date actualIn;

	@Column(name = "actual_out")
	@Temporal(TemporalType.TIME)
	private java.util.Date actualOut;

	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@Column(name = "modified_date")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "modified_by")
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
	
	  public String getEmpCode() { return empCode; }
	  
	  public void setEmpCode(String empCode) { this.empCode = empCode; }
	 


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

	public java.util.Date getActualIn() {
		return actualIn;
	}

	public void setActualIn(java.util.Date actualIn) {
		this.actualIn = actualIn;
	}

	public java.util.Date getActualOut() {
		return actualOut;
	}

	public void setActualOut(java.util.Date actualOut) {
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
