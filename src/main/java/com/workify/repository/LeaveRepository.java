package com.workify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workify.entity.DAOleaveInfo;

@Repository
public interface LeaveRepository extends JpaRepository<DAOleaveInfo, Integer>{

}
