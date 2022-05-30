package com.admin.model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirlineResponse {

	private long airlineId;
	private String airlineName;
	private String contactNumber;
	private String contactAddress;
	private Boolean blockedStatus = false; 
}
