package com.symbio.demo.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.symbio.demo.domain.business.repositories.AccountRepo;
import com.symbio.demo.domain.common.repositories.UserRepo;
import com.symbio.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate businessJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate commonJdbcTemplate;

	@Override
	public Object findById(Long id) {
		return accountRepo.findById(id).get();
	}

	@Override
	public Object findDefaultById(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public Object findAll() {
		Map<String,Object> result = new HashMap<>();
		result.put("account", accountRepo.findAll());
		result.put("user", userRepo.findAll());
		
		result.put("business", businessJdbcTemplate.queryForList("select * from account"));
		result.put("common", commonJdbcTemplate.queryForList("select * from user"));
		return result;
	}

}
