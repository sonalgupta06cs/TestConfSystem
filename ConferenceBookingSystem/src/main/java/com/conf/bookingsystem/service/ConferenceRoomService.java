package com.conf.bookingsystem.service;

import java.util.List;

import com.conf.bookingsystem.entity.ConferenceRoom;

public interface ConferenceRoomService {

	List<ConferenceRoom> getConferenceRooms();
	ConferenceRoom getConferenceRoomById(int confid);
	void createMeetingRoom(ConferenceRoom confRoom);
	boolean deleteConferenceRoomById(int confid);
	List<ConferenceRoom> getAvailableRooms();
	ConferenceRoom update(ConferenceRoom room, int id);

}
