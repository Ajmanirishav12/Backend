package com.workify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workify.entity.DAOUserShiftDetails;

@Repository
public interface UserShiftDetailsRepository extends JpaRepository<DAOUserShiftDetails,Integer> {

	DAOUserShiftDetails findByUserId(Integer userId);
}
