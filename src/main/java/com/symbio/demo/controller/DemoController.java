package com.symbio.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.symbio.demo.domain.business.models.Account;
import com.symbio.demo.services.UserService;

@RequestMapping(value = "/demo")
@RestController
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/test1",method = RequestMethod.GET)
	public ResponseEntity<Object> test1(){
		return new ResponseEntity<>(userService.findById(1l), HttpStatus.OK);
	}
	@RequestMapping(value = "/test2",method = RequestMethod.GET)
	public ResponseEntity<Object> test2(){
		return new ResponseEntity<>(userService.findDefaultById(1l), HttpStatus.OK);
	}
	@RequestMapping(value = "/test3",method = RequestMethod.GET)
	public ResponseEntity<Object> test3(){
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

}
