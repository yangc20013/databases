package com.symbio.demo.services;

public interface UserService {
	Object findById(Long id);
	Object findDefaultById(Long id);
	Object findAll();

}
