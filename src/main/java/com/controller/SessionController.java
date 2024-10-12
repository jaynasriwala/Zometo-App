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
import com.services.MailerService;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController 
{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	
	@Autowired
	MailerService mailerService;
	
	//add customer
	@PostMapping("/customersignup")
	public CustomerEntity customerSignup(@RequestBody CustomerEntity customerEntity)
	{
		
		customerRepository.save(customerEntity);
		return customerEntity;
	}
	
	//add restaurant
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
	
	
	//send otp to customer
	@GetMapping("sendotpforrecoverpassword/{email}")
	public String sendOtpForRecoverPassword(@PathVariable("email") String email)
	{
		CustomerEntity customerEntity = customerRepository.findByEmail(email);
		
		if(customerEntity!=null)
		{
			int otp = (int)(Math.random()*1000000);
			
			System.out.println("otp:- "+otp);
			
			mailerService.sendMailForOtp(email, otp);
			
			customerEntity.setOtp(otp);
			
			customerRepository.save(customerEntity);
			
			return "success";
		}
		
		else {
			return null;
		}
		
	}
	
	//update password
	@GetMapping("/updatepasswod/{email}/{password}/{cpassword}/{otp}")
	public String updatePassword(@PathVariable("email") String email,@PathVariable("password") String password,@PathVariable("cpassword") String cpassword,@PathVariable("otp") Integer otp)
	{
		
		CustomerEntity customerEntity = customerRepository.findByEmail(email);
		
		if(! password.equals(cpassword))
		{
			return "password and cpassword not same";
		}
		
		if(customerEntity == null)
		{
			return "invelid";
		}
		else
		{
			if(otp == -1 || customerEntity.getOtp().intValue() != otp)
			{
				return "invelid otp";
			}
			else
			{
				customerEntity.setPassword(password);
				customerEntity.setOtp(-1);
				customerRepository.save(customerEntity);
				
				return "password updeted";
			}
		}
	}
	

}
