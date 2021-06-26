package it.polito.tdp.crimes.model;

public class Vicino implements Comparable{
	
	Integer id;
	Double distanza;
	public Vicino(Integer id, Double distanza) {
		super();
		this.id = id;
		this.distanza = distanza;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getDistanza() {
		return distanza;
	}
	public void setDistanza(Double distanza) {
		this.distanza = distanza;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Vicino v = (Vicino) o;
		return this.distanza.compareTo(v.distanza);
	}
	@Override
	public String toString() {
		return id + ": " + distanza;
	}
	
	

}
