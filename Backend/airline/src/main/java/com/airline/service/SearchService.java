package com.airline.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.model.SearchModel;
import com.airline.model.SearchResponse;
import com.airline.repository.InventoryDataService;

@Service
public class SearchService {

	@Autowired
	private InventoryDataService searchDataService;
	public List<SearchResponse> getAllFlightDetails(SearchModel searchModel) throws ParseException {
		return searchDataService.getFlightData(searchModel);
	}

	
}
 