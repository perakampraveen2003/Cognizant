package com.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.booking.entity.Booking;
import com.booking.service.BookingService;

@SpringBootTest
class BookingApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Autowired
	private BookingService bookingService;
	
	@Test
	public void getTicketDataByPnr() {
		Booking booking = bookingService.getTicket("PNR925089");
		assertEquals("PNR925089",booking.getPnrNumber());
	}
	
	@Test
	public void checkUserDataByEmail() {
		List<Booking> bookings = bookingService.getHistory("praveen@gmail.com");
		assertEquals(bookings.size(), 1);
	}

}
