package com.workify.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workify.entity.DAOAttendanceInfo;

@Repository
public interface AttendanceRepository extends JpaRepository<DAOAttendanceInfo, Integer> {

	DAOAttendanceInfo findByUserIdAndDate(Integer userID, Date date);

	
	List<DAOAttendanceInfo> findByUserIdOrderByDateDesc(Integer userID);

}
