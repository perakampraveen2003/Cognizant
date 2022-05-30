package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airline.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>{

	@Query(value = "select * from airlines.airline where airline_name = ?1",nativeQuery = true)
	public Airline findByAirline(String string);

	@Query(value = "select distinct airline_name from airlines.airline",nativeQuery = true)
	public List<String> getDistinctAirlines();

}