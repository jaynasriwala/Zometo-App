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
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer orderId;
	
//	customerId;
	@ManyToOne
	@JoinColumn(name = "customerId")
	CustomerEntity customerEntity;
	
//	cartId;
	@ManyToOne
	@JoinColumn(name = "cartId")
	CartEntity cartEntity;
	
	Float totalPaid;
	String authCode;
	Integer status;
	String paymentType;
	String orderDate;
	
}
