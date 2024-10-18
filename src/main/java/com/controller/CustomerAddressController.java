package com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerAdderssEntity;
import com.repository.CustomerAddressRepository;

@RestController
@RequestMapping("/api/private/customeraddress")
public class CustomerAddressController 
{
	
	@Autowired
	CustomerAddressRepository addressRepository;
	
	// add customer address
	@PostMapping("/addAddress")
	public ResponseEntity<String> addCustomerAddress(@RequestBody CustomerAdderssEntity adderssEntity)
	{
		addressRepository.save(adderssEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body("Record Added Successfully");
	}
	
	//read customerAddress by customer Id
	@GetMapping("customeraddress/{customerId}")
	public ResponseEntity<?> getCustomerAddressById(@PathVariable("customerId") Integer customerId) 
	{
		List<CustomerAdderssEntity> customerAddress = addressRepository.findByCustomerCustomerId(customerId);
			if(customerAddress.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
			}
			else {
			    return ResponseEntity.status(HttpStatus.FOUND).body(customerAddress);
			}
		}
	
	//delete address
	@DeleteMapping("/deletebyid/{addressId}")
	public ResponseEntity<?> deleteById(@PathVariable("addressId")Integer addressId)
	{
		addressRepository.deleteById(addressId);
		return ResponseEntity.status(HttpStatus.OK).body("Record Deleted Successfully");
	}
	
	//delete By cutomer id
	@DeleteMapping("/deletebycustomerid/{customerId}")
	public ResponseEntity<?> deleteByCustomerId(@PathVariable("customerId")Integer customerId)
	{
		addressRepository.deleteByCustomerCustomerId(customerId);
		return ResponseEntity.status(HttpStatus.OK).body("Record Deleted Successfully");
	}
	
	// update customerAddress by Id
		@PutMapping("/updatecustomeraddress")
		public ResponseEntity<?> updateCustomerAddress(@RequestBody CustomerAdderssEntity customerAddressEntity) {
				
				addressRepository.save(customerAddressEntity);
				return new ResponseEntity<>("Record Updated Successfully",HttpStatus.OK);
			
		}
}
