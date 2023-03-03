package com.ldeepak.springboot.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ldeepak.springboot.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
