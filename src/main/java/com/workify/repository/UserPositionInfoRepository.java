package com.workify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workify.entity.DAOUserPositionInfo;

@Repository
public interface UserPositionInfoRepository extends JpaRepository<DAOUserPositionInfo, Long> {

	DAOUserPositionInfo findByUserId(Integer integer);

}
