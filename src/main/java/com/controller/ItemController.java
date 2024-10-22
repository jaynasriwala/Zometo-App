package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.ItemEntity;
import com.repository.ItemRepository;

@RestController
@RequestMapping("/api/private/item")
public class ItemController 
{

	@Autowired
	ItemRepository itemRepository;
	
	
	//add item
	@PostMapping("/additem")
	public ResponseEntity<?> addItem(@RequestBody ItemEntity itemEntity)
	{
		itemRepository.save(itemEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(itemEntity);
	}
	
	//get all item
	@GetMapping("/getallitem")
	public ResponseEntity<?> getAllItem(ItemEntity itemEntity)
	{
		List<ItemEntity> item = itemRepository.findAll();
		if(item.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.OK).body(item);
		}
	}
	
		//get item by id
		@GetMapping("/getitembyid/{itemId}")
		public ResponseEntity<?> getItemById(@PathVariable("itemId") Integer itemId, ItemEntity itemEntity)
		{
			Optional<ItemEntity> opitem = itemRepository.findById(itemId);
			if(opitem.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
			}
			else
			{
				return ResponseEntity.status(HttpStatus.OK).body(opitem);
			}
		}
		
		
	
}
