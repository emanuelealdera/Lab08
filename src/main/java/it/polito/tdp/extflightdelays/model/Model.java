package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private ExtFlightDelaysDAO dao;
	private Map <Integer, Airport> aeroportiIdTrack;
	private Map <Integer, Flight> voliIdTrack;
	private List <Tratta> tratte; 
	private Graph <Airport, Tratta> graph;
	
	public Model () {
		dao = new ExtFlightDelaysDAO();
		aeroportiIdTrack = dao.loadAllAirports();
		voliIdTrack = dao.loadAllFlights();
		tratte = new ArrayList <> () ;
		costruisciTratte(voliIdTrack);
	}
	
	public String getResult (int minDistance) {
		
		graph = new SimpleWeightedGraph <> (Tratta.class);
		String str="";
		for (Tratta t : tratte) {
			if (t.getAverage() > minDistance) {
				if (!graph.containsVertex(aeroportiIdTrack.get(t.getIdAirport1())))
					graph.addVertex(aeroportiIdTrack.get(t.getIdAirport1()));
				if (!graph.containsVertex(aeroportiIdTrack.get(t.getIdAirport2())))
					graph.addVertex(aeroportiIdTrack.get(t.getIdAirport2()));
				graph.addEdge(aeroportiIdTrack.get(t.getIdAirport1()), aeroportiIdTrack.get(t.getIdAirport2()), t);
			}
		}
		
		
		
		str+="Numero vertici: " + graph.vertexSet().size()+"   Numero archi: "+ graph.edgeSet().size()+"\n";
		
		for (Tratta t : graph.edgeSet()) {
			str+= t.toString()+"\n";
		}
		return str;
 	}
	
	
	private void costruisciTratte (Map <Integer, Flight> voliIdTrack) {
		for (Flight f : voliIdTrack.values()) {
			Tratta temp = new Tratta (f.getOriginAirportId(), f.getDestinationAirportId());
			
			if (tratte.contains(temp) == false ) {
				tratte.add(temp);
				tratte.get(tratte.size()-1).updateSum(f.getDistance());
			}
			
			else {
				for (Tratta t : tratte) {
					if (t.equals(temp))
						t.updateSum(f.getDistance());
				}
			}
		}
	}
	
	

}
