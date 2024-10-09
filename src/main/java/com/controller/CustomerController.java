package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerEntity;
import com.repository.CustomerRepository;

@RestController
public class CustomerController 
{

	@Autowired
	CustomerRepository customerRepository;
	
	// get all customer
	@GetMapping("/allcustomer")
	public List<CustomerEntity> getAllCustomer(CustomerEntity customerEntity)
	{
		return customerRepository.findAll();
	}
	
	
}
