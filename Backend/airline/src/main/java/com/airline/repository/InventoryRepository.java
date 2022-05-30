package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airline.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	@Query(value = "select distinct from_place from airlines.inventory",nativeQuery = true)
	List<String> getDistinctFromPlace();

	@Query(value = "select distinct to_place from airlines.inventory",nativeQuery = true)
	List<String> getDistinctToPlace();

	@Query(value = "select * from airlines.inventory where flight_number = ?1",nativeQuery = true)
	Inventory findByFlightId(long flight);


}
