package com.conf.bookingsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conf.bookingsystem.entity.Reservations;
import com.conf.bookingsystem.exceptions.ErrorMessages;
import com.conf.bookingsystem.exceptions.ResourceServiceExceptions;
import com.conf.bookingsystem.repository.ConferenceRoomRepository;
import com.conf.bookingsystem.repository.ReservationsRepository;
import com.conf.bookingsystem.service.ReservationService;

@Service
@Transactional
public class ReservationsServiceImp  implements ReservationService{

	@Autowired
	ReservationsRepository reservationsRepository;
	
	@Autowired
	ConferenceRoomRepository conferenceRoomRepository;

	@Override
	public List<Reservations> getReservations() {
		return (List<Reservations>) reservationsRepository.findAll();
	}

	@Override
	public Reservations findById(int id) {
		 Optional<Reservations> optReservation = reservationsRepository.findById(id); // returns java8 optional
		    if (optReservation.isPresent()) {
		        return optReservation.get();
		    } else {
		        throw new ResourceServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		    }
	}

	@Override
	public Boolean deleteReservationsById(int id) {
		 Optional<Reservations> optReservation = reservationsRepository.findById(id); // returns java8 optional
		 if (optReservation.isPresent()) {
			 reservationsRepository.delete(optReservation.get());
			 conferenceRoomRepository.makeConfRoomReservationAvailable(optReservation.get().getRoomId());
			 return true;
		    } else {
		    	throw new ResourceServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		    }
	}

	@Override
	public List<Reservations> findByName(String roomname) {
		return (List<Reservations>) reservationsRepository.findByName(roomname);
	}

	@Override
	public Reservations createReservation(Reservations reservation) throws Exception {
		System.out.println("Reservation to create: " + reservation);

		String availablity = conferenceRoomRepository.isAvailableConfRoom(reservation.getRoomId());
		if(availablity.equalsIgnoreCase("true")) {
			Reservations r = reservationsRepository.save(reservation);
			conferenceRoomRepository.makeConfRoomReservationUnAvailable(reservation.getRoomId());
			return r;
		}
		throw new ResourceServiceExceptions(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage()); // or throw customized error message
	}
}
