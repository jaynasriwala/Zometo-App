package com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.entity.RestaurantEntity;
import com.repository.RestaurantRepository;

@RestController
public class RestaurantController 
{

	@Autowired
	RestaurantRepository restaurantRepository;
	
	//get all Restaurant where active is 1
	@GetMapping("allrestaurant/{active}")
	public List<RestaurantEntity> getAllResturant(@PathVariable("active") Integer active)
	{
		return restaurantRepository.findByActive(active);
	}
	
	
	//get all Restaurant where active is 1 and pincode is given
	@GetMapping("allrestaurant/{active}/{pincode}")
	public List<RestaurantEntity> getAllResturantByPincode(@PathVariable("active") Integer active,@PathVariable("pincode") Integer pincode)
	{
		
		
		return restaurantRepository.findByActiveAndPincode(active,pincode);
	}
	
	
	
}
