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
@Table(name = "user_position_info")
public class DAOUserPositionInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_position_id")
	Long userPosId;
	@Column(name = "user_id")
	Integer userId;
	@Column(name = "emp_code")
	String empCode;
	@Column(name = "designation")
	String designation;
	@Column(name = "grade")
	String grade;
	@Column(name = "employment_category")
	String empCategory;
	@Column(name = "employment_status")
	String empStatus;
	@Column(name = "employment_type")
	String empType;
	@Column(name = "department")
	String department;
	@Column(name = "location")
	String location;
	@Column(name = "l1_manager_id")
	Integer l1ManagerId;
	@Column(name = "l2_manager_id")
	Integer l2ManagerId;
	@Column(name = "hr_manager_id")
	Integer hrManagerId;
	@Column(name = "org_id")
	Integer orgId;
	@Column(name = "is_active")
	Boolean isActive;
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
	public Long getUserPosId() {
		return userPosId;
	}
	public void setUserPosId(Long userPosId) {
		this.userPosId = userPosId;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEmpCategory() {
		return empCategory;
	}
	public void setEmpCategory(String empCategory) {
		this.empCategory = empCategory;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getL1ManagerId() {
		return l1ManagerId;
	}
	public void setL1ManagerId(Integer l1ManagerId) {
		this.l1ManagerId = l1ManagerId;
	}
	public Integer getL2ManagerId() {
		return l2ManagerId;
	}
	public void setL2ManagerId(Integer l2ManagerId) {
		this.l2ManagerId = l2ManagerId;
	}
	public Integer getHrManagerId() {
		return hrManagerId;
	}
	public void setHrManagerId(Integer hrManagerId) {
		this.hrManagerId = hrManagerId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
