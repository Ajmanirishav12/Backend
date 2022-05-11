package com.workify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workify.entity.DAOOrganization;

@Repository
public interface OrganizationRepository extends JpaRepository<DAOOrganization,Integer> {

	
	
}
