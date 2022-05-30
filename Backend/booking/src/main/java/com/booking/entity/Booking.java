package com.booking.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	@Id
	private String pnrNumber;
	private long flightId;
	private String name;
	private String email;
	private int no_of_seats;
	private String passengers;
	private String mealType;
	private double fare;
	private String bookStatus = "Booked";  
	private String arivalTime;
	private String arivalDate;
	private String fromPlace;
	private String toPlace;
	private String returnDate;
	
	public Booking(String pnr) {
		this.pnrNumber=pnr;
	}

}
