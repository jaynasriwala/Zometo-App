package com.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer>
{
//	@Query("select * from RestaurantEntity where active = 1")
//	List <RestaurantEntity> findByActive(Integer actives);
	
	List<RestaurantEntity> findByActive(Integer active);

	List<RestaurantEntity> findByActiveAndPincode(Integer active,Integer pincode);
	
	RestaurantEntity findByEmail(String email);
}
