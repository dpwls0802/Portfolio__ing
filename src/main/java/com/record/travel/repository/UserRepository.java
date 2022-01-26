package com.record.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.record.travel.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
