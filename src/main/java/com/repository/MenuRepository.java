package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {

	Optional<MenuEntity> findByRestaurantRestaurantId(Integer restaurantId);

}
