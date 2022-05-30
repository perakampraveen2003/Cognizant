package com.booking.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingModel {
	private String name;
	private String email;
	private int no_of_seats;
	private String time;
	List<PassengerModel> passengers = new ArrayList<>();
	private String mealType;
	private String tripType;
	private String returnDate;
}
