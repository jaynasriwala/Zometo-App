package com.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.RestaurantEntity;
import com.repository.RestaurantRepository;

@RestController
@RequestMapping("/api/private/restaurant")
public class RestaurantController 
{

	@Autowired
	RestaurantRepository restaurantRepository;
	
	// Read All Restaurant
		@GetMapping("/allrestaurants")
		public ResponseEntity<?> getAllRestaurants() 
		{
			List<RestaurantEntity> restaurants = restaurantRepository.findAll();
			
			if(restaurants.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(restaurants);
			}
		}
		
		@GetMapping("/nearest-restaurants/{lat}/{log}")
	    public List<RestaurantEntity> getNearestRestaurants(@PathVariable("lat") double latitude,@PathVariable("log") double longitude) {
	        return restaurantRepository.findNearestRestaurants(latitude, longitude,PageRequest.of(0, 5));
	    }
		
		// Read Restaurant by Id
			@GetMapping("/restaurants/{restaurantId}")
			public ResponseEntity<?> getRestaurantsById(@PathVariable("restaurantId") Integer restaurantId) {
				Optional<RestaurantEntity> restaurants = restaurantRepository.findById(restaurantId);
				if(restaurants.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
				}else {
					return ResponseEntity.status(HttpStatus.OK).body(restaurants.get()) ;
				}
			}
	
	
	//get all Restaurant where active is 1
	@GetMapping("/allrestaurant/{active}")
	public ResponseEntity<?> getAllResturant(@PathVariable("active") Integer active)
	{
		List<RestaurantEntity> restaurant = restaurantRepository.findByActive(active);
		
		
		if(restaurant.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
		}
		else
		{
		return ResponseEntity.status(HttpStatus.OK).body(restaurant) ;
		}
	}
	
	
	//get all Restaurant where active is 1 and pincode is given
	@GetMapping("/allrestaurant/{active}/{pincode}")
	public ResponseEntity<?> getAllResturantByPincode(@PathVariable("active") Integer active,@PathVariable("pincode") Integer pincode)
	{
		List<RestaurantEntity> restaurant = restaurantRepository.findByActiveAndPincode(active,pincode);
		
		
		if(restaurant.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
		}
		else
		{
		return ResponseEntity.status(HttpStatus.OK).body(restaurant) ;
		}
	}
	
	// Delete Restaurant By Id
		@DeleteMapping("deletrestaurant/{restaurantId}")
		public ResponseEntity<?> deleteRestaurantById(@PathVariable("restaurantId") Integer restaurantId) {
			Optional<RestaurantEntity> restaurant = restaurantRepository.findById(restaurantId);
			if(restaurant.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
			}else {
				restaurantRepository.deleteById(restaurantId);
				return ResponseEntity.status(HttpStatus.OK).body("Record deleted");
			}
		}
		
		// Update Restaurant by Id
		@PutMapping("updaterestaurant")
		public ResponseEntity<String> updateRestaurant( @RequestBody RestaurantEntity restaurantEntity) {
				restaurantRepository.save(restaurantEntity);
				return ResponseEntity.status(HttpStatus.OK).body("Record updated");
			}
	
	
}
