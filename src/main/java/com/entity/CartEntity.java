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
@Table(name = "carts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer	cartId;//	PK
	
	//Many carts can belong to one customer
	@ManyToOne
	@JoinColumn(name = "customerId")
	CustomerEntity customerEntity;
	
	//Each cart is associated with one restaurant
	@ManyToOne
	@JoinColumn(name = "restaurantId")
	RestaurantEntity restaurantEntity;
	
	Integer offerAmount;
	Integer totalQty;
}
