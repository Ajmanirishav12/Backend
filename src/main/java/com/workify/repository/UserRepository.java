package com.workify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workify.entity.DAOUser;

@Repository
public interface UserRepository extends JpaRepository<DAOUser, Integer> {
	DAOUser findByUsername(String username);
	
	DAOUser findByofficialMail(String officialmail);

}
