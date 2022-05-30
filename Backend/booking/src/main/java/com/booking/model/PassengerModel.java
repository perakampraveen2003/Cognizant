package com.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerModel {

	private String passengerName;
	private int passengerAge;
	private String passengerGender;
	private String seatNo;
}

