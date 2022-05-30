package com.airline.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.entity.Airline;
import com.airline.model.AirlineModel;
import com.airline.repository.AirlineRepository; 

@Service
public class AirlineService {

	@Autowired
	private AirlineRepository airlineRepository;

	public Airline registerAirline(AirlineModel airlineModel) {
		Airline check = airlineRepository.findByAirline(airlineModel.getAirlineName());
		System.out.println(airlineModel);
		if(!Objects.nonNull(check))
		{
			Airline airline = new Airline();
			airline.setAirlineName(airlineModel.getAirlineName());
			airline.setContactAddress(airlineModel.getContactAddress());
			airline.setContactNumber(airlineModel.getContactNumber());
			airline = airlineRepository.save(airline); 
			return airline;
		}
		else
			return null;
		
	}

	public Airline blockAirline(String airline) {
		Airline airlineEntity = airlineRepository.findByAirline(airline);
		if(Objects.nonNull(airlineEntity))
		{
			airlineEntity.setBlockedStatus(true);
			return airlineRepository.saveAndFlush(airlineEntity);
		}
		else return null;
	}

	public Airline unBlockAirline(String airline) {
		Airline airlineEntity = airlineRepository.findByAirline(airline);
		if(Objects.nonNull(airlineEntity))
		{
			airlineEntity.setBlockedStatus(false);
			return airlineRepository.saveAndFlush(airlineEntity);
		}
		else return null;
	}

	public List<String> getAirlines() {
		return airlineRepository.getDistinctAirlines();
	}
}

