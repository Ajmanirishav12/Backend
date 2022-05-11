package com.workify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workify.entity.DAOUserLeaveBalance;

public interface UserLeaveBalanceRepository extends JpaRepository<DAOUserLeaveBalance, Long>{

	
	DAOUserLeaveBalance findByUserId(Integer appUserId);

}