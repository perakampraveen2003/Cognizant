package com.airline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airline.entity.Airline;
import com.airline.service.AirlineService;

@SpringBootTest
class AirlineApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Autowired
	private AirlineService airlineService;
	
	
	@Test
	public void checkBlockAirline() {
		Airline testEntity = airlineService.blockAirline("Nexon Business PVT limited");
		assertEquals(testEntity.getBlockedStatus(), true);
	}

}
