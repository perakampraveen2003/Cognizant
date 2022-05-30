package com.airline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryModel {
	private long flightNumber;
	private String airline;
	private String fromPlace;
	private String toPlace;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String schedule;
	private String instrumentUsed;
	private int total_business_class_seats;
	private int total_economy_class_seats;
	private double ticketPrice;
	private int no_of_rows;
	private String mealType;
}
