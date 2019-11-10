package com.pra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pra.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
