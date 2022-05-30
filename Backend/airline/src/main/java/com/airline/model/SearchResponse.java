package com.airline.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchResponse {
	
	private long flightNumber;
	private String startDate;
	private String airlineName;
	private double price;
	public SearchResponse(long fNo,String sdate,String airline,double price) {
		this.flightNumber=fNo;
		this.startDate=sdate;
		this.airlineName=airline;
		this.price=price;
	}
}

