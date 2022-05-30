package com.airline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchModel {

		private String date;
		private String time;
		private String fromPlace;
		private String toPlace;
		private String tripType;
}
