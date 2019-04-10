package com.symbio.demo.domain.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.symbio.demo.domain.common.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}