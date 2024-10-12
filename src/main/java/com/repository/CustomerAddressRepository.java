package com.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CustomerAdderssEntity;

public interface CustomerAddressRepository extends JpaRepository<CustomerAdderssEntity, Integer>{

	List<CustomerAdderssEntity> findByCustomerCustomerId(Integer id);
}
