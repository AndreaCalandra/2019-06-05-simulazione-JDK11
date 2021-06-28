package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;

public class Ev {
	
	enum EventType {
		EVENTO,		// succede qualcosa
		RITORNO,	// torna ad essere operatilo l'agente
	} ;
	
	Event e;
	private EventType type;
	public Event getE() {
		return e;
	}
	public void setE(Event e) {
		this.e = e;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public Ev(Event e, EventType type) {
		super();
		this.e = e;
		this.type = type;
	}

	
	
}
