package com.airline.repository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.airline.model.SearchModel;
import com.airline.model.SearchResponse;

@Service
public class InventoryDataService {
	
	@Autowired
	private EntityManager em;

	public List<SearchResponse> getFlightData(SearchModel searchModel) throws ParseException {
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchModel.getDate());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = format.format(startDate); 
		String fromPlace = searchModel.getFromPlace();
		String toPlace = searchModel.getToPlace();
		String tripType = searchModel.getTripType();
		String time = searchModel.getTime();
		String select = "select inventory.flight_number,inventory.start_date,inventory.start_time,inventory.airline_name,inventory.ticket_price";
		String from = " from airlines.inventory inventory left join airlines.airline airline on inventory.airline_id = airline.airline_id";
		String where = " where start_date = :sDate and from_place = :fromPlace and to_place = :toPlace and start_time = :time and blocked_status = 0";
		Query q;
		List<SearchResponse> response = new ArrayList<>();
		q = em.createNativeQuery(select.toString().concat(from.toString()).concat(where.toString()), "SearchMapping");
		q.setParameter("sDate", sDate);
		q.setParameter("fromPlace", fromPlace);
		q.setParameter("toPlace", toPlace);
		q.setParameter("time", time);
		response = q.getResultList();
		return response.stream().map(s->{
			SearchResponse resp = new SearchResponse();
			resp=s;
			if(tripType.equalsIgnoreCase("round trip"))
				resp.setPrice(s.getPrice()*2);
			return resp;
		}).collect(Collectors.toList());
	}

}
