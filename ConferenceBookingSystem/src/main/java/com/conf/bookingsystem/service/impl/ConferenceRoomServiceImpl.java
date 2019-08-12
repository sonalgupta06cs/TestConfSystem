package com.conf.bookingsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conf.bookingsystem.entity.ConferenceRoom;
import com.conf.bookingsystem.exceptions.ErrorMessages;
import com.conf.bookingsystem.exceptions.ResourceServiceExceptions;
import com.conf.bookingsystem.repository.ConferenceRoomRepository;
import com.conf.bookingsystem.service.ConferenceRoomService;

@Service
@Transactional
public class ConferenceRoomServiceImpl implements ConferenceRoomService {

	@Autowired
	ConferenceRoomRepository confRoomRepository;
	
	@Override
	public List<ConferenceRoom> getConferenceRooms() {

		List<ConferenceRoom> returnedConfRooms = confRoomRepository.findAll();
		return returnedConfRooms;
	}

	@Override
	public ConferenceRoom getConferenceRoomById(int confid) {
		
		Optional<ConferenceRoom> optMeetingRoom = confRoomRepository.findById(confid);
		if(optMeetingRoom.isPresent()) {
			return optMeetingRoom.get();
		} else {
			throw new ResourceServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
	}

	@Override
	public void createMeetingRoom(ConferenceRoom confRoom) {
		
		confRoomRepository.save(confRoom);
		
	}

	@Override
	public boolean deleteConferenceRoomById(int confid) {
		
		Optional<ConferenceRoom> optMeetingRoom = confRoomRepository.findById(confid);
		if(optMeetingRoom.isPresent()) {
			confRoomRepository.deleteById(confid);
			return true;
		} else {
			throw new ResourceServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
	}

	@Override
	public ConferenceRoom update(ConferenceRoom confroom, int id) {
		Optional<ConferenceRoom> optMeetingRoom = confRoomRepository.findById(id); // returns java8 optional
	    if (optMeetingRoom.isPresent()) {
	    	ConferenceRoom newRoom = optMeetingRoom.get();
	    	newRoom.setName(confroom.getName());
	    	newRoom.setCapacity(confroom.getCapacity());
	    	newRoom.setLocation(confroom.getLocation());

	       ConferenceRoom updatedroom = confRoomRepository.save(newRoom);
	        return updatedroom;
	    } else {
	    	throw new ResourceServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
	    }
	}
	
	@Override
	public List<ConferenceRoom> getAvailableRooms() {
		
		return (List<ConferenceRoom>) confRoomRepository.findAvailableMeetingRoom();
	}
}
