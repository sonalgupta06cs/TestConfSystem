package com.conf.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.conf.bookingsystem.entity.ConferenceRoom;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Integer> {

	public static final String FIND_ROOMS = "SELECT * FROM conference_rooms where available='true'";

	@Query(value = FIND_ROOMS, nativeQuery = true)
	List<ConferenceRoom> findAvailableMeetingRoom();
	
	@Modifying
    @Query(value="update conference_rooms set available = 'false' where id = :roomId ", nativeQuery = true)	
	void makeConfRoomReservationUnAvailable(@Param("roomId") int roomId);

	@Modifying
    @Query(value="update conference_rooms set available = 'true' where id = :roomId ", nativeQuery = true)
	void makeConfRoomReservationAvailable(@Param("roomId") int roomId);

	@Query(value="select available from conference_rooms where id = :roomId ", nativeQuery = true)
	String isAvailableConfRoom(@Param("roomId") int roomId);

}
