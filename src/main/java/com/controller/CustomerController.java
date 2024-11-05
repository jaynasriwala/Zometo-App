package com.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerEntity;
import com.repository.CustomerRepository;

@RestController
@RequestMapping("/api/private/customer")
public class CustomerController 
{

	@Autowired
	CustomerRepository customerRepository;
	
	// get all customer
	@GetMapping("/allcustomer")
	public ResponseEntity<?> getAllCustomer(CustomerEntity customerEntity)
	{
		List<CustomerEntity> customer = customerRepository.findAll();
		
		if(customer.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.FOUND).body(customer);
		}
	}
	// get customer by id
	@GetMapping("/customerbyid/{customerid}")
	public ResponseEntity<?> getCustomerById(@PathVariable("customerid") Integer customerid)
	{
		
		Optional<CustomerEntity> op = customerRepository.findById(customerid);
		
		if(op.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.FOUND).body(op.get()); 
		}
	}
	
//	//delete customer by id
//		@DeleteMapping("/deletecustomer/{customerId}")
//		public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") Integer customerId , CustomerEntity customerEntity)
//		{
//			Optional<CustomerEntity> customer = customerRepository.findById(customerId);
//			if(customer.isEmpty()) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");	
//			}else {
//				customerRepository.deleteById(customerId);
//				return ResponseEntity.status(HttpStatus.OK).body("Record deleted");
//			}
//		}
		
		//update the customer
		 @PutMapping("/updatecustomer")
		 public ResponseEntity<String> updateCustomerProfile(@RequestBody CustomerEntity customerEntity) 
		 {
		 	customerRepository.save(customerEntity);
		 	return ResponseEntity.status(HttpStatus.OK).body("Record Updated");
		 }
	
}
