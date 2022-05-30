package com.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.admin.entity.AdminDetails;
import com.admin.model.AirlineModel;
import com.admin.model.AirlineResponse;
import com.admin.model.InventoryResponse;
import com.admin.model.InventoryModel;
import com.admin.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RestTemplate rest;
	
	private String airlineRegisterURL = "lb://AIRLINE/api/v1.0/flight/airline/register";
	private String inventoryRegisterURL = "lb://AIRLINE/api/v1.0/flight/airline/inventory/add";
	private String blockURL = "lb://AIRLINE/api/v1.0/flight/airline/block/{airline}";
	private String unBlockURL = "lb://AIRLINE/api/v1.0/flight/airline/unblock/{airline}";
	private String delUrl = "lb://AIRLINE/api/v1.0/flight/airline/delete/{flight}";
	
	public AdminDetails getAdmin(String uname) {
		return this.adminRepository.findByUsername(uname);
	}

	public void createAdmin() {
		String pwd = encoder.encode("admin123");
		 adminRepository.save(new AdminDetails(1,"admin123@fbs.com",pwd));	
	}

	public AirlineResponse registerAirline(AirlineModel airlineModel) {
		
		return rest.exchange(airlineRegisterURL,HttpMethod.POST,new HttpEntity<>(airlineModel),AirlineResponse.class).getBody();
	}

	public InventoryResponse addInventory(InventoryModel inventoryModel) {
		return rest.exchange(inventoryRegisterURL,HttpMethod.POST,new HttpEntity<>(inventoryModel),InventoryResponse.class).getBody();
	}

	public AirlineResponse unBlockAirline(String airline) {
		return rest.exchange(unBlockURL,HttpMethod.PUT,null,AirlineResponse.class,airline).getBody();
	}

	public AirlineResponse blockAirline(String airline) {
		return rest.exchange(blockURL,HttpMethod.PUT,null,AirlineResponse.class,airline).getBody();
	}

	public void deleteFlight(long flight) {
		this.rest.delete(delUrl, flight);
	}
}
