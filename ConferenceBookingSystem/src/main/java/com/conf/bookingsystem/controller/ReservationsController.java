package com.conf.bookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conf.bookingsystem.entity.Reservations;
import com.conf.bookingsystem.service.ReservationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reservation")
public class ReservationsController {
	
	@Autowired
    private ReservationService reservationServiceImp;
	
	@ApiOperation( value="The Get All Reservations for Conference Rooms Details Web Service end point.. ")
	@GetMapping(value = "/get", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Reservations> getAllReservations() {
		
		System.out.println("Fetching all reservations");
        List<Reservations> rooms = reservationServiceImp.getReservations();
        return rooms;
	}
	
	@ApiOperation( value="The Create Conference Rooms Booking Details Web Service end point.. ")
	@PostMapping(value="/book", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> createReservation(@RequestBody Reservations reservation) throws Exception{
        System.out.println("Creating Reservation "+ reservation);
        Reservations r = reservationServiceImp.createReservation(reservation);
        if(r != null) {
        	return new ResponseEntity<String>("Booking Created Successfully", HttpStatus.CREATED);
        } else
        	return new ResponseEntity<String>("Failed to Book", HttpStatus.NO_CONTENT);
    }
	
	@ApiOperation( value="The Delete Conference Rooms Booking Details By Reservationid Web Service end point.. ")
	@DeleteMapping(value="/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> deleteReservation(@PathVariable("id") int id){
		Reservations reservation = reservationServiceImp.findById(id);
        if (reservation == null) {
            return new ResponseEntity<String>("No such Reservation", HttpStatus.NOT_FOUND);
        }
        reservationServiceImp.deleteReservationsById(id);
        return new ResponseEntity<String>("Reservation Deleted", HttpStatus.NO_CONTENT);
    }
}
