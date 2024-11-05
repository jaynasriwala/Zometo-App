package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer>
{
//	@Query("select * from RestaurantEntity where active = 1")
//	List <RestaurantEntity> findByActive(Integer actives);
	
	List<RestaurantEntity> findByActive(Integer active);

	List<RestaurantEntity> findByActiveAndPincode(Integer active,Integer pincode);
	
	RestaurantEntity findByEmail(String email);
	
	
	 @Query(value = """
		        SELECT r FROM RestaurantEntity r 
		        ORDER BY 
		        (6371 * acos(cos(radians(:lat)) * cos(radians(r.lat)) * cos(radians(r.log) - radians(:log)) + sin(radians(:lat)) * sin(radians(r.lat))))
		        ASC
		        """)
		    List<RestaurantEntity> findNearestRestaurants(
		        @Param("lat") double latitude,
		        @Param("log") double longitude,
		        org.springframework.data.domain.Pageable pageable
		    );
}
