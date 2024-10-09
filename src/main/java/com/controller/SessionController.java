package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerEntity;
import com.entity.RestaurantEntity;
import com.repository.CustomerRepository;
import com.repository.RestaurantRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController 
{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@PostMapping("/customersignup")
	public CustomerEntity customerSignup(@RequestBody CustomerEntity customerEntity)
	{
		
		customerRepository.save(customerEntity);
		return customerEntity;
	}
	
	@PostMapping("/restaurantsignup")
	public String restaurantSignup(@RequestBody RestaurantEntity restaurantEntity )
	{
		System.out.println(restaurantEntity.getTitle());
		restaurantRepository.save(restaurantEntity);
		return "succes";
	}
	
	
	//login for customer restaurant
	@GetMapping("/authentication/{email}/{password}")
	public String authentication(@PathVariable("email") String email,@PathVariable("password") String password)
	{
		
		CustomerEntity loginCustomer = customerRepository.findByEmail(email);
		RestaurantEntity loginResturent = restaurantRepository.findByEmail(email);
		
		if(loginCustomer == null && loginResturent == null)
		{
			return "invelid email";
		}
		else
		{
			if(loginCustomer != null)
			{
				if(!(loginCustomer.getPassword().matches(password)))
				{
					return "invelid password";
				}
			}
			if(loginResturent != null)
			{
				if(!(loginResturent.getPassword().matches(password)))
				{
					return "invelid password";
				}
			}	
		}
		return "login";	
	}
	

}
