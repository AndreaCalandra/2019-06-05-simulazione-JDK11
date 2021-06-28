package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulazione {
	
	Graph<Vertice, DefaultWeightedEdge> grafo;
	PriorityQueue<Event> queue;
	
	// input
	int anno;
	int mese;
	int giorno;
	int n;
	
	Map<Integer, Vertice> idMap;	// mappa per gli id dei vertici
	Map<Integer, Vertice> mappa;	// mappa id detective, vertice dove si trova
	Vertice best;
	
	List<Event> eventi = new ArrayList<>();
	
	
	//output
	int malGestiti;
	
	
	
	public Simulazione(Graph<Vertice, DefaultWeightedEdge> g, int a, int m, int gio, int n, Vertice best, List<Event> eventi) {
		this.grafo = g;
		this.anno = a;
		this.mese = m;
		this.giorno = gio;
		this.n = n;
		this.malGestiti = 0;
		this.best = best;
		this.eventi = eventi;
	}
	
	public void inizializza() {
		mappa = new HashMap<>();
		for (int i = 1; i<=n; i++) {
			mappa.put(i, best);
		}
		this.queue = new PriorityQueue<>();
		for (Event e: eventi) {
			queue.add(e);
		}
		for (Vertice v: grafo.vertexSet()) {
			idMap.put(v.id, v);
		}
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			System.out.println(e);
			processEvent(e);
		}
	}
	
	public void processEvent(Event e) {
		int districtId = e.getDistrict_id();
		Vertice vertice = idMap.get(districtId);
		Vertice vicino = null;
		int idIspettore = -1;
		Double pesoMin = 10000.00;
		for (Map.Entry<Integer, Vertice> entry : mappa.entrySet()) {
	        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	        
	        // cerco l'id del più vicino
	        DefaultWeightedEdge edge = grafo.getEdge(vertice, entry.getValue());
	        Double peso = grafo.getEdgeWeight(edge);
	        
	        if (peso <= pesoMin) {
	        	pesoMin = peso;
	        	vicino = entry.getValue();
	        	idIspettore = entry.getKey();
	        }
	    }
		
		Double tempo = (pesoMin*1000) / (60*16.6666);	// tempo in ore
		if (tempo > 15) {
			malGestiti++;
		}
		
		mappa.put(idIspettore, vicino);
		
	}

}
