package com.admin.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {

	private long inventoryId;
	private long flightNumber; 
	private AirlineResponse airline;
	private String airlineName;
	private String fromPlace;
	private String toPlace;
	private Date startDate;
	private String startTime;
	private Date endDate;
	private String endTime;
	private String schedule;
	private String instrumentUsed;
	private int total_business_class_seats;
	private int total_economy_class_seats;
	private double ticketPrice;
	private int no_of_rows;
	private String mealType;
}
