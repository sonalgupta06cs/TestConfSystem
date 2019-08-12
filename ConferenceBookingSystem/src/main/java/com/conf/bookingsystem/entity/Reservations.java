package com.conf.bookingsystem.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name="reservations")
@ApiModel(description="All details about the Reservation of conf rooms. ")
public class Reservations {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "reservation_id")
	private int reservationId;
	
	@Column(name="room_id")
	private int roomId;
	
	@Column(name="purpose")
	private String purpose;
	
	@Column(name="date_begin")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
	//@Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateBegin;
	
	@Column(name="date_end")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
	//@Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateEnd;

	public Reservations() {
		super();
	}

	public Reservations(int id, int roomId, String purpose, Timestamp dateBegin, Timestamp dateEnd) {
		super();
		this.reservationId = id;
		this.roomId = roomId;
		this.purpose = purpose;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
	}

	public Reservations(int roomId, String purpose, Timestamp dateBegin, Timestamp dateEnd) {
		super();
		this.roomId = roomId;
		this.purpose = purpose;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int id) {
		this.reservationId = id;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Timestamp dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "Reservations [id=" + reservationId + ", roomId=" + roomId + ", purpose=" + purpose + ", dateBegin=" + dateBegin
				+ ", dateEnd=" + dateEnd + "]"; 
	}
	
	
	
}
