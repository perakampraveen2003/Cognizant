package com.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pid;
	private String passengerName;
	private int passengerAge;
	private String passengerGender;
	private String seatNo;
	@ManyToOne
	@JoinColumn(name = "pnrNumber")
	Booking booking;

}
