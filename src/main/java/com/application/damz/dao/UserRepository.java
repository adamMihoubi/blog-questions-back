package com.application.damz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.damz.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

	public User findByEmail(String email);

}
