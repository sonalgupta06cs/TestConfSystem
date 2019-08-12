package com.conf.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.conf.bookingsystem.entity.Reservations;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Integer>{
	
	@Query(value = "SELECT * FROM reservations WHERE room_id = (select id from conference_rooms where name = ?1)", nativeQuery = true)
	List<Reservations> findByName(String roomname);

}
