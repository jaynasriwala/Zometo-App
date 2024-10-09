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
@Table(name = "customer_address")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerAdderssEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer addressId;
	String title;
	String addressLine;
	Integer pincode;
	Float lat;
	Float log;

	@ManyToOne
	@JoinColumn(name = "customerId")
	CustomerEntity customer;
}