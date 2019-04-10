package com.symbio.demo.domain.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.symbio.demo.domain.business.models.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

}
