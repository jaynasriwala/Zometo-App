package com.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.MenuEntity;
import com.repository.MenuRepository;

@RestController
@RequestMapping("/api/private/menu")
public class MenuController 
{
	
	@Autowired
	MenuRepository menuRepository;

	//add menu
	@PostMapping("/addmenu")
	public ResponseEntity<?> addMenu(@RequestBody MenuEntity menuEntity)
	{
		menuRepository.save(menuEntity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Record Added");
	}
	
	//red All menus
	@GetMapping("/getmenus")
	public ResponseEntity<?> getAllMenus() {
		List<MenuEntity> menus = menuRepository.findAll();
		if(menus.isEmpty()) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
		}else
		{
			return ResponseEntity.status(HttpStatus.OK).body(menus);
		}
	}
	
	//get menu
	@GetMapping("/getmenubyid/{menuId}")
	public ResponseEntity<?> getMenuById(@PathVariable("menuId") Integer menuId)
	{
		Optional<MenuEntity> op =  menuRepository.findById(menuId);
		
		if(op.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.OK).body(op.get());
		}
	}
	
	//read menu By RestaurantId
		@GetMapping("menubyrestaurantid/{restaurantId}")
		public ResponseEntity<?> getMenuByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
			Optional<MenuEntity> menus = menuRepository.findByRestaurantRestaurantId(restaurantId);
			if (menus.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(menus.get());
			}
		}
	
	// delete menu
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) 
	{
		menuRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body("SuccessFully Deleted Record");
	}
	
	
	//update menu
	@PutMapping("/updatemenu")
	public ResponseEntity<?> updateMenu(@RequestBody MenuEntity menuEntity) {
		menuRepository.save(menuEntity);
		return ResponseEntity.status(HttpStatus.OK).body("Record Updated");
	}
}
