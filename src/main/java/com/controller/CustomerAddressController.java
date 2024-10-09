package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerAdderssEntity;
import com.repository.CustomerAddressRepository;

@RestController
public class CustomerAddressController 
{
	
	@Autowired
	CustomerAddressRepository addressRepository;
	
	// add customer address
	@PostMapping("/addAddress")
	public String addCustomerAddress(@RequestBody CustomerAdderssEntity adderssEntity)
	{
		addressRepository.save(adderssEntity);
		return "success";
	}
	
	//read customerAddress by customer Id
//	@GetMapping("customeraddress/{customerId}")
//	public CustomerAdderssEntity getCustomerAddressById(@PathVariable("customerId") Integer customerId) 
//	{
//		List<CustomerAdderssEntity> customerAddress = addressRepository.findByCustomerId(customerId);
//			if(customerAddress.isEmpty()) {
//				return null;
//			}
//			else {
//				CustomerAdderssEntity customerAddressEntity = customerAddress.get);
//			    return customerAddressEntity;
//			}
//		}
	
	
	//delete address
	@DeleteMapping("/deletebyid/{addressId}")
	public String deleteById(@PathVariable("addressId")Integer addressId)
	{
		addressRepository.deleteById(addressId);
		return "success";
	}
	
	//delete By cutomer id
	@DeleteMapping("/deletebycustomerid/{customerId}")
	public String deleteByCustomerId(@PathVariable("customerId")Integer customerId)
	{
		addressRepository.deleteById(customerId);
		return "success";
	}
}
