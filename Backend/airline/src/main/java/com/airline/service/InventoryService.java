package com.airline.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.entity.Airline;
import com.airline.entity.Inventory;
import com.airline.exception.ResourceNotFoundException;
import com.airline.model.InventoryModel;
import com.airline.repository.AirlineRepository;
import com.airline.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private AirlineRepository airlineRepository;
	
	@Autowired
	private EntityManager em;

	public Inventory addInventory(InventoryModel inventoryModel) throws ParseException, ResourceNotFoundException {
		String startDate = inventoryModel.getStartDate();
		String endDate = inventoryModel.getEndDate();
		Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		Inventory inventory = new Inventory();
		Airline obj = airlineRepository.findByAirline(inventoryModel.getAirline());
		if(Objects.nonNull(obj)) 
				inventory.setAirline(obj);
		else 
				throw new ResourceNotFoundException("Airline resource not found");
		inventory.setAirlineName(inventoryModel.getAirline());
		inventory.setEndDate(end);
		inventory.setEndTime(inventoryModel.getEndTime());
		inventory.setFlightNumber(inventoryModel.getFlightNumber()); 
		inventory.setFromPlace(inventoryModel.getFromPlace());
		inventory.setInstrumentUsed(inventoryModel.getInstrumentUsed());
		inventory.setMealType(inventoryModel.getMealType());
		inventory.setNo_of_rows(inventoryModel.getNo_of_rows());
		inventory.setSchedule(inventoryModel.getSchedule());
		inventory.setStartDate(start);
		inventory.setStartTime(inventoryModel.getStartTime());
		inventory.setTicketPrice(inventoryModel.getTicketPrice());
		inventory.setToPlace(inventoryModel.getToPlace());
		inventory.setTotal_business_class_seats(inventoryModel.getTotal_business_class_seats());
		inventory.setTotal_economy_class_seats(inventoryModel.getTotal_economy_class_seats());
		inventory = inventoryRepository.save(inventory);
		return inventory;
	}

	public double getTicketPrice(String tripType, long flightId) {
		Query q = em.createNativeQuery("select distinct ticket_price from airlines.inventory where flight_number = :flightId", "TicketPricing");
		q.setParameter("flightId", flightId);
		List<Inventory> entity = q.getResultList();
		double price = entity.get(0).getTicketPrice();
		if(tripType.equalsIgnoreCase("round trip")) price = price*2;
		return price;
	}

	public List<String> getFromPlaces() {
		return inventoryRepository.getDistinctFromPlace();
	}

	public List<String> getToPlaces() {
		return inventoryRepository.getDistinctToPlace();
	}

	public Inventory findFlightById(long flight) {
		return inventoryRepository.findByFlightId(flight);
	}

	public List<Inventory> getFlights() {
		// TODO Auto-generated method stub
		return inventoryRepository.findAll();
	}

	public void delFlight(long flight) {
		Inventory i =this.inventoryRepository.findByFlightId(flight);
		this.inventoryRepository.deleteById(i.getInventoryId());
		
	}

}
