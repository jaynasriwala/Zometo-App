package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "cartItems")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cartItemId;

	//Each cart item belongs to one cart
	@ManyToOne
	@JoinColumn(name = "cardId")
	CartEntity cartEntity;
	
	@ManyToOne
	@JoinColumn(name = "itemId")
	ItemEntity itemEntity;
	
	@ManyToOne
	@JoinColumn(name = "menuId")
	
	MenuEntity menuEntity;
	
	Integer qty;
}
