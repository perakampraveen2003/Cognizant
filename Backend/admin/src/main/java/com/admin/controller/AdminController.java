package com.admin.controller;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.entity.AirlineWrapper;
import com.admin.model.AirlineModel;
import com.admin.model.AirlineResponse;
import com.admin.model.InventoryModel;
import com.admin.model.InventoryResponse;
import com.admin.model.JwtRequest;
import com.admin.model.JwtResponse;
import com.admin.service.AdminService;
import com.admin.service.JwtUserDetailsService;
import com.admin.utils.JwtTokenUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1.0/admin")
@SecurityRequirement(name = "admin")
public class AdminController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/test")
	public String get() {
		return "Hello";
	}
	
//	@PostConstruct
//	public void createAdmin() {
//		adminService.createAdmin();
//	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@DeleteMapping("airline/delete/{flight}")
	public void deleteFlight(@PathVariable long flight) {
		adminService.deleteFlight(flight);
	}
	@PostMapping("airline/register")
	public ResponseEntity<String> registerAirline(@RequestBody AirlineModel airlineModel){
		AirlineResponse acknowledgement = adminService.registerAirline(airlineModel);
		return Objects.nonNull(acknowledgement)?new ResponseEntity<String>("Registered Successfully",HttpStatus.OK):new ResponseEntity<String>("Already Registered",HttpStatus.OK);
//		return new ResponseEntity<AirlineResponse>(acknowledgement,Objects.nonNull(acknowledgement) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("airline/inventory/add")
	public ResponseEntity<String> addInventory(@RequestBody InventoryModel inventoryModel) {
		InventoryResponse acknowledgement = adminService.addInventory(inventoryModel);
		return new ResponseEntity<String>("Flight added Successfully",HttpStatus.OK);
//		return new ResponseEntity<InventoryResponse>(acknowledgement,Objects.nonNull(acknowledgement) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("airline/block/{airline}")
	public ResponseEntity<AirlineWrapper<String>> blockAirline(@PathVariable("airline") String airline)
	{
		AirlineResponse acknowledgement = adminService.blockAirline(airline);
//		return new ResponseEntity<AirlineResponse>(acknowledgement,Objects.nonNull(acknowledgement) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		return Objects.nonNull(acknowledgement)?new ResponseEntity<AirlineWrapper<String>>(new AirlineWrapper<String>("Requested Airline has been Blocked Successfully"),HttpStatus.OK):new ResponseEntity<AirlineWrapper<String>>(new AirlineWrapper<String>("No AIrline Found to Block"),HttpStatus.OK);
	}
	
	
	@PutMapping("airline/unblock/{airline}")
	public ResponseEntity<AirlineWrapper<String>> unBlockAirline(@PathVariable("airline") String airline)
	{
		AirlineResponse acknowledgement = adminService.unBlockAirline(airline);
//		return new ResponseEntity<AirlineResponse>(acknowledgement,Objects.nonNull(acknowledgement) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		return Objects.nonNull(acknowledgement)?new ResponseEntity<AirlineWrapper<String>>(new AirlineWrapper<String>("Requested Airline has been Unblocked Successfully"),HttpStatus.OK):new ResponseEntity<AirlineWrapper<String>>(new AirlineWrapper<String>("No AIrline Found to UnBlock"),HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
