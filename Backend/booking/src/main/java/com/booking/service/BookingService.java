package com.booking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.booking.entity.Booking;
import com.booking.exception.ResourceNotFoundException;
import com.booking.model.Airline;
import com.booking.model.BookingModel;
import com.booking.model.Inventory;
import com.booking.model.PassengerModel;
import com.booking.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RestTemplate rest;
	
	String url = "lb://AIRLINE/api/v1.0/flight/ticket-price/{tripType}/{flightId}";
	String airlineUrl = "lb://AIRLINE/api/v1.0/flight/airline/{flightId}";
	Map<String,Object> urlVariables = new HashMap<>();
	public Booking saveBooking(BookingModel model, long flightId) {
		Booking booking = new Booking();
		String pnrNumber = "PNR"+new Random().nextInt(999999);
		booking.setPnrNumber(pnrNumber);
		booking.setFlightId(flightId);
		booking.setEmail(model.getEmail());
		booking.setMealType(model.getMealType());
		booking.setName(model.getName());
		booking.setNo_of_seats(model.getNo_of_seats());
		Inventory inventoryEntity = rest.exchange(airlineUrl, HttpMethod.GET, null,Inventory.class, flightId).getBody();
		booking.setArivalDate(inventoryEntity.getStartDate().toString());
		booking.setArivalTime(inventoryEntity.getStartTime());
		booking.setFromPlace(inventoryEntity.getFromPlace());
		booking.setToPlace(inventoryEntity.getToPlace());
		booking.setReturnDate(model.getReturnDate());
		String passenger = "";
		for(PassengerModel p:model.getPassengers()) {
			passenger = passenger+ 
						p.getPassengerName()+
						","+
						p.getPassengerAge()+
						","+
						p.getPassengerGender()+
						","+
						p.getSeatNo()+
						"@";
		}
		//System.out.println(passenger.substring(0, passenger.length()-1));
		booking.setPassengers(passenger.substring(0, passenger.length()-1));
		urlVariables.put("tripType", model.getTripType());
		urlVariables.put("flightId", flightId);
		ResponseEntity<Double> price = rest.getForEntity(url, double.class,urlVariables);
		System.out.println("---------------"+price.getBody());
		booking.setFare(price.getBody()*model.getNo_of_seats());
		Booking booked = bookingRepository.save(booking);
		return booked;
	}

	public Booking cancleTicket(String pnr) throws ResourceNotFoundException {
		try{
			Booking book = bookingRepository.findById(pnr).get();
			book.setBookStatus("Cancelled");
			return bookingRepository.save(book);
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("No Booking found with pnr "+pnr);
		}
	}

	public Booking getTicket(String pnr) {
		return bookingRepository.getTicketDetails(pnr);
	}

	public List<Booking> getHistory(String email) {
		return bookingRepository.getBookingsByEmail(email.toUpperCase());
	}

	
}
