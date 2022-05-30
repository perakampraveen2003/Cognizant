package com.booking.controller;

import java.util.List;
import java.util.Objects;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.booking.entity.Booking;
import com.booking.entity.Hello;
import com.booking.exception.ResourceNotFoundException;
import com.booking.model.BookingModel;
import com.booking.service.BookingService;


@RestController
@RequestMapping("/api/v1.0/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	
//	@GetMapping(value = "test")
//	public Hello<String> get() {
//		return new Hello<String>("Hai");
//	}
//	@GetMapping(value = "test")
//	public String get() {
//		return "Hai";
//	}

	@PostMapping("/{flightId}") 
	public ResponseEntity<Booking> save(@RequestBody BookingModel bookingModel, @PathVariable("flightId") long flightId) {
		System.out.println(bookingModel);
		Booking booking = bookingService.saveBooking(bookingModel,flightId);
		return new ResponseEntity<Booking>(booking,Objects.nonNull(booking)?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/ticket/{pnr}")
	public ResponseEntity<Booking> getTicket(@PathVariable("pnr") String pnr){
		Booking ticket = bookingService.getTicket(pnr); 
		return Objects.nonNull(ticket) ? new ResponseEntity<Booking>(ticket,HttpStatus.OK) :  new ResponseEntity<Booking>(ticket,HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/history/{email}")
	public ResponseEntity<List<Booking>> getHistoryByEmailId(@PathVariable("email") String email){
		List<Booking> bookings = bookingService.getHistory(email);
		return !CollectionUtils.isEmpty(bookings) ? new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK) :  new ResponseEntity<List<Booking>>(bookings,HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/cancel/{pnr}")
	public Booking cancleTicket(@PathVariable("pnr") String pnr) throws ResourceNotFoundException{
		return bookingService.cancleTicket(pnr);  
	}
		
}
