package it.polito.tdp.crimes.model;

public class Vertice {

	int id;
	double lat;
	double lon;
	
	public Vertice(int id, double lat, double lon) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	
	
}
