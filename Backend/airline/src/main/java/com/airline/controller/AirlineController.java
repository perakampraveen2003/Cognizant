package com.airline.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.entity.Airline;
import com.airline.entity.Inventory;
import com.airline.exception.ResourceNotFoundException;
import com.airline.model.AirlineModel;
import com.airline.model.InventoryModel;
import com.airline.model.SearchModel;
import com.airline.model.SearchResponse;
import com.airline.service.AirlineService;
import com.airline.service.InventoryService;
import com.airline.service.SearchService;
///api/v1.0/flight/airline/flights
@RestController
@RequestMapping("/api/v1.0/flight")
public class AirlineController {

	@Autowired
	private AirlineService airlineService;
	@Autowired 
	private InventoryService inventoryService;
	@Autowired
	private SearchService searchService; 
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String TOPIC = "Airline";
			
	@GetMapping("/airline/airlines")
	public List<String> getDistinctAirlines(){
		return airlineService.getAirlines();
	}
	
	@DeleteMapping("/airline/delete/{flight}")
	public void delFlight(@PathVariable long flight) {
		inventoryService.delFlight(flight);
	}
	
	@GetMapping("/airline/{flight}")
	public Inventory getAirline(@PathVariable("flight") long flight) {
		return inventoryService.findFlightById(flight); 
	}
	
	@PostMapping("/airline/register")  
	public ResponseEntity<Airline> registerAirline(@RequestBody AirlineModel airlineModel){
		System.out.println(airlineModel);
		Airline airline = airlineService.registerAirline(airlineModel);
//		if(Objects.nonNull(airline))
//				kafkaTemplate.send(TOPIC, airline.toString()+" Successfully registered");
//		else
//			kafkaTemplate.send(TOPIC, " Already registered");
		return new ResponseEntity<Airline>(airline,Objects.nonNull(airline)?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("airline/flights")
	public List<Inventory> getFlights(){
		return inventoryService.getFlights();
	}
	
	@PostMapping("/airline/inventory/add")
	public ResponseEntity<Inventory> addInventory(@RequestBody InventoryModel inventoryModel) throws ParseException, ResourceNotFoundException{
		Inventory inventory = inventoryService.addInventory(inventoryModel);
//		kafkaTemplate.send(TOPIC, inventory.toString()+" Successfully registered");
		return new ResponseEntity<Inventory>(inventory,Objects.nonNull(inventory)?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/airline/block/{airline}")
	public ResponseEntity<Airline> blockAirline(@PathVariable("airline") String airline)
	{
		Airline airlineEntity = airlineService.blockAirline(airline);
//		if(Objects.nonNull(airlineEntity))
//			kafkaTemplate.send(TOPIC, "Requested airline "+airline+" Blocked successfully");
		return new ResponseEntity<Airline>(airlineEntity,Objects.nonNull(airlineEntity)?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
	
	
	@PutMapping("/airline/unblock/{airline}")
	public ResponseEntity<Airline> unBlockAirline(@PathVariable("airline") String airline)
	{
		Airline airlineEntity = airlineService.unBlockAirline(airline);
//		if(Objects.nonNull(airlineEntity))
//			kafkaTemplate.send(TOPIC, "Requested airline "+airline+" Unblocked successfully");
		return new ResponseEntity<Airline>(airlineEntity,Objects.nonNull(airlineEntity)?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/ticket-price/{tripType}/{flightId}")
	public double getPrice(@PathVariable String tripType, @PathVariable long flightId) {
		return inventoryService.getTicketPrice(tripType,flightId);
	}
	
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchModel searchModel) throws ParseException{
		List<SearchResponse> response = searchService.getAllFlightDetails(searchModel);
//		System.out.println("2022-05-04 00:00:00".substring(0, 10));
		response.stream().forEach(item->{
			item.setStartDate(item.getStartDate().substring(0, 10));
		});
		return Objects.nonNull(response) ? new ResponseEntity<List<SearchResponse>>(response,HttpStatus.OK) :  new ResponseEntity<List<SearchResponse>>(response,HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping("fromPlaces")
	public List<String> getFromPlaces(){
		return inventoryService.getFromPlaces(); 
	}
	
	@GetMapping("toPlaces")
	public List<String> getToPlaces(){
		return inventoryService.getToPlaces(); 
	}
	
}
