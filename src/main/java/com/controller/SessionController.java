package com.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dto.LoginDto;
import com.entity.CustomerEntity;
import com.entity.RestaurantEntity;
import com.repository.CustomerRepository;
import com.repository.RestaurantRepository;
import com.services.MailerService;


@RestController
@RequestMapping("/api/public/session")
public class SessionController 
{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	MailerService mailerService;
	
	//add customer
	@PostMapping("/customersignup")
	public ResponseEntity<?> customerSignup(@RequestBody CustomerEntity customerEntity)
	{
		// read plain text password
		//String plainPassword = customerEntity.getPassword();
				
	// encrypt plain text password
		//String encPassword = encoder.encode(plainPassword);
		//customerEntity.setPassword(encPassword);
		
		customerRepository.save(customerEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerEntity);
	}
	
	//add restaurant
	@PostMapping("/restaurantsignup")
	public ResponseEntity<?> restaurantSignup(@RequestBody RestaurantEntity restaurantEntity )
	{
		
		// read plain text password
		//String plainPassword = restaurantEntity.getPassword();
				
	// encrypt plain text password
		//String encPassword = encoder.encode(plainPassword);
		//restaurantEntity.setPassword(encPassword);
		
		System.out.println(restaurantEntity.getTitle());
		restaurantRepository.save(restaurantEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantEntity);
	}
	
	
	//login for customer restaurant
	@PostMapping("/authentication")
	public ResponseEntity<?> authentication(@RequestBody LoginDto loginDto)
	{
		
		CustomerEntity loginCustomer = customerRepository.findByEmail(loginDto.getEmail());
		RestaurantEntity loginResturent = restaurantRepository.findByEmail(loginDto.getEmail());
		
		if(loginCustomer == null && loginResturent == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invelid mail");
		}
		else
		{
			if(loginCustomer != null)
			{
				if(!(loginCustomer.getPassword().matches(loginDto.getPassword())))
				{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invelid password");
				}
			}
			if(loginResturent != null)
			{
				if(!(loginResturent.getPassword().matches(loginDto.getPassword())))
				{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invelid password");
				}
			}	
		}
		return ResponseEntity.status(HttpStatus.OK).body("login");
	}
	
	
	//send otp to customer
	@GetMapping("sendotpforrecoverpassword/{email}")
	public ResponseEntity<?> sendOtpForRecoverPassword(@PathVariable("email") String email)
	{
		CustomerEntity customerEntity = customerRepository.findByEmail(email);
		
		if(customerEntity!=null)
		{
			int otp = (int)(Math.random()*1000000);
			
			System.out.println("otp:- "+otp);
			
			mailerService.sendMailForOtp(email, otp);
			
			customerEntity.setOtp(otp);
			
			customerRepository.save(customerEntity);
			
			return ResponseEntity.status(HttpStatus.OK).body("Opt Send");
		}
		
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
		}
		
	}
	
	//update password
	@GetMapping("/updatepasswod/{email}/{password}/{cpassword}/{otp}")
	public ResponseEntity<?> updatePassword(@PathVariable("email") String email,@PathVariable("password") String password,@PathVariable("cpassword") String cpassword,@PathVariable("otp") Integer otp)
	{
		
		CustomerEntity customerEntity = customerRepository.findByEmail(email);
		
		if(! password.equals(cpassword))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("password and cpassword not same");
		}
		
		if(customerEntity == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
		}
		else
		{
			if(otp == -1 || customerEntity.getOtp().intValue() != otp)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invelid otp");
			}
			else
			{
				customerEntity.setPassword(password);
				customerEntity.setOtp(-1);
				customerRepository.save(customerEntity);
				
				return ResponseEntity.status(HttpStatus.OK).body("password updeted");
			}
		}
	}
	

}
