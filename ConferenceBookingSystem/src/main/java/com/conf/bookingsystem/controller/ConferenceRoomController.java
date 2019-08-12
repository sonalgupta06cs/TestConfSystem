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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conf.bookingsystem.entity.ConferenceRoom;
import com.conf.bookingsystem.entity.ReservationDate;
import com.conf.bookingsystem.service.ConferenceRoomService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/conferencerooms")
public class ConferenceRoomController {

	@Autowired
	ConferenceRoomService confRoomServiceImpl;

	@ApiOperation( value="The Get Conference Rooms Details Web Service end point.. ")
	@GetMapping(value = "/get", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ConferenceRoom> getConferenceRooms() {

		List<ConferenceRoom> returnedConfRooms = confRoomServiceImpl.getConferenceRooms();

		return returnedConfRooms;
	}

	@ApiOperation( value="The Get Conference Rooms Details By Id Web Service end point.. ")
	@GetMapping(value = "/get/{confid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ConferenceRoom> getConferenceRoomById(@PathVariable("confid") int confid) {

		System.out.println("Fetching Conference Room with id " + confid);

		ConferenceRoom returnedConfRoom = confRoomServiceImpl.getConferenceRoomById(confid);
		
		System.out.println("returnedConfRoom "+returnedConfRoom);
		if (returnedConfRoom == null) {
			return new ResponseEntity<ConferenceRoom>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ConferenceRoom>(HttpStatus.OK);
	}
	
	@ApiOperation( value="The Get available Conference Rooms Details Web Service end point.. ")
	@GetMapping(value = "/getAvailableRooms", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ConferenceRoom> getAvailableRooms() {

		System.out.println("Fetching All Available Conference Room");

		List<ConferenceRoom> returnedConfRooms = confRoomServiceImpl.getAvailableRooms();
		
		return returnedConfRooms;
	}
	
	@ApiOperation( value="The Create Conference Rooms Web Service end point.. ")
	@PostMapping(value="/create", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createConferenceRoom(@RequestBody ConferenceRoom confRoom){
		
		System.out.println("Creating Conference Room "+ confRoom.getName());
		confRoomServiceImpl.createMeetingRoom(confRoom);
        return new ResponseEntity<String>("Conference Room Created Successfully", HttpStatus.CREATED);
		
	}
	
	@ApiOperation( value="The Delete Conference Rooms Details By Id Web Service end point.. ")
	@DeleteMapping(value="/delete/{confid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> deleteUser(@PathVariable("confid") int confid){
		ConferenceRoom room = confRoomServiceImpl.getConferenceRoomById(confid);
        if (room == null) {
            return new ResponseEntity<String>("No such Conference room", HttpStatus.NOT_FOUND);
        }
        confRoomServiceImpl.deleteConferenceRoomById(confid);
        return new ResponseEntity<String>("Conference Room Deleted", HttpStatus.NO_CONTENT);
    }
	
	@ApiOperation( value="The Update Conference Rooms Details Web Service end point.. ")
	@PutMapping(value="/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ConferenceRoom> updateUserPartially(@RequestBody ConferenceRoom room){
		ConferenceRoom meetingroom = confRoomServiceImpl.getConferenceRoomById(room.getId());
        if(meetingroom ==null){
            return new ResponseEntity<ConferenceRoom>(HttpStatus.NOT_FOUND);
        }
        ConferenceRoom r1 = confRoomServiceImpl.update(room, room.getId());
        return new ResponseEntity<ConferenceRoom>(r1, HttpStatus.OK);
    }

}
