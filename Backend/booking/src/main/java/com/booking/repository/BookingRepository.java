package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String>{

	@Query(value = "select * from flight.booking where upper(email) = ?1",nativeQuery = true)
	List<Booking> getBookingsByEmail(String email);
	
	@Query(value = "select * from flight.booking where pnr_number = ?1",nativeQuery = true)
	Booking getTicketDetails(String pnr);
}
 