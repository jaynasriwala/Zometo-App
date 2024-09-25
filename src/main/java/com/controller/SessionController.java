package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.CustomerEntity;
import com.repository.CustomerRepository;

public class SessionController 
{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("customersignup")
	public CustomerEntity customerSignup(@RequestBody CustomerEntity customerEntity)
	{
		
		customerRepository.save(customerEntity);
		
		return customerEntity;
	}
}
