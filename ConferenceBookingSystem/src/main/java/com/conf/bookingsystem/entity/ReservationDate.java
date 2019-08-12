package com.conf.bookingsystem.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ReservationDate {
	
	private Timestamp df;
	private Timestamp dt;
	
	public ReservationDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReservationDate(Timestamp df, Timestamp dt) {
		super();
		this.df = df;
		this.dt = dt;
	}

	public Date getDf() {
		return df;
	}

	public void setDf(Timestamp df) {
		this.df = df;
	}

	public Timestamp getDt() {
		return dt;
	}

	public void setDt(Timestamp dt) {
		this.dt = dt;
	}
	

}
