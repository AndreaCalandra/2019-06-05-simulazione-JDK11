package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	Graph<Vertice, DefaultWeightedEdge> grafo;
	EventsDao dao;
	List<Integer> distretti;
	List<Vertice> vertici;
	
	public Model () {
		dao = new EventsDao ();
		distretti = new ArrayList<>();
		vertici = new ArrayList<>();
	}
	
	public void creaGrafo(int anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// vertici
		
		distretti = dao.listDistrict();
		vertici = dao.getVertici(anno);
		Graphs.addAllVertices(grafo, vertici);
		
		// archi
		
		for (Vertice v1: vertici) {
			for (Vertice v2: vertici) {
				if (v1.id != v2.id) {
					if (grafo.getEdge(v1, v2) == null) {
						Double distanzaMedia = LatLngTool.distance(new LatLng(v1.lat,v1.lon), 
								new LatLng(v2.lat, v2.lon), 
								LengthUnit.KILOMETER);
						Graphs.addEdgeWithVertices(grafo, v1, v2, distanzaMedia);
					}
				}
			}
		}
		
	}
	
	public int nVertici () {
		return grafo.vertexSet().size();
	}
	
	public int nArchi () {
		return grafo.edgeSet().size();
	}
	
	public List<Vicino> getVicini (Vertice distretto) {
		List<Vicino> vicini = new ArrayList<>();
		
		for (Vertice v: Graphs.neighborListOf(grafo, distretto)) {
			Double distanzaMedia = LatLngTool.distance(new LatLng(distretto.lat,distretto.lon), 
					new LatLng(v.lat, v.lon), 
					LengthUnit.KILOMETER);
			Vicino vicino = new Vicino (v.id, distanzaMedia);
			vicini.add(vicino);
		}
		Collections.sort(vicini);
		
		return vicini;
	}
	
	public Set<Vertice> getVertici() {
		return grafo.vertexSet();
	}
}
