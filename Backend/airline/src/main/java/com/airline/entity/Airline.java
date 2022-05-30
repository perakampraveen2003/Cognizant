package com.airline.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airline {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long airlineId;
	private String airlineName;
	private String contactNumber;
	private String contactAddress;
	private Boolean blockedStatus = false; 

	public Airline(long airlineId) {
		this.airlineId=airlineId;
	}
}
