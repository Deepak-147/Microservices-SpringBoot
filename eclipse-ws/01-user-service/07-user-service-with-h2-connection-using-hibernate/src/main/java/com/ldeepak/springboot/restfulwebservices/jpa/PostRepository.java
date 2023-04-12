package com.ldeepak.springboot.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ldeepak.springboot.restfulwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
