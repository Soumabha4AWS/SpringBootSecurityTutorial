package com.springboot.security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.security.demo.entity.AppUsers;

public interface AppUsersDetailsRepository extends JpaRepository<AppUsers, Long>{
	AppUsers findByUserName(String name) ;
}
