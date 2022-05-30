package com.airline.entity;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;

import com.airline.model.SearchResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
		name = "SearchMapping",
		classes = {
				@ConstructorResult(
						targetClass = SearchResponse.class,
						columns = {
								@ColumnResult(name = "flight_number",type = Long.class),
								@ColumnResult(name = "start_date", type = String.class),
								@ColumnResult(name = "airline_name",type = String.class),
								@ColumnResult(name = "ticket_price",type = double.class)
						}
						)
		}
		)
@SqlResultSetMapping(
		name = "TicketPricing",
		classes = {
				@ConstructorResult(
						targetClass = Inventory.class,
						columns = {
								@ColumnResult(name = "ticket_price",type = double.class)
						}
						)
		}
		)
public class Inventory {

	@Id
	@GeneratedValue
	private long inventoryId;
	private long flightNumber; 
	@ManyToOne
	@JoinColumn(name = "airlineId")
	private Airline airline;
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
	
	public Inventory(double ticketPrice) {
		this.ticketPrice=ticketPrice;
	}
	
}
