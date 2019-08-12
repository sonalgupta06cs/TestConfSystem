package com.conf.bookingsystem.service;

import java.util.List;
import com.conf.bookingsystem.entity.Reservations;

public interface ReservationService {
	
	public Reservations createReservation(Reservations reservation) throws Exception;
    public List<Reservations> getReservations();
    public Reservations findById(int id);
    public Boolean deleteReservationsById(int id);
    public List<Reservations> findByName(String name);
}
