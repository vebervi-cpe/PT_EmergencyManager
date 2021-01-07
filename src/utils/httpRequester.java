package utils;

import java.util.ArrayList;
import java.util.List;

import model.Fire;
import model.Station;
import model.Truck;

public final class httpRequester {
	
	// Variable pour pouvoir tester rapidement en générant des camions et des feux.
	private static Boolean localTest = true;

	private static String get(String endpoint) {
		return "TODO";
	}
	
	private static String post(String endpoint, String data) {
		return "TODO";
	}
	
	public static List<Truck> getAllTrucks(String endpoint) {
		List<Truck> trucks = new ArrayList<Truck>();
		
		if(localTest) {
			trucks = factory_getTrucks();
		} else {
			String results = get(endpoint);
			// TODO
		}
		
		return trucks;
	}
	
	public static List<Fire> getAllFires(String endpoint) {
		List<Fire> fires = new ArrayList<Fire>();

		if(localTest) {
			fires = factory_getFires();
		} else {
			String results = get(endpoint);
			// TODO
		}

		return fires;
	}
	
	public static List<Station> getAllStations(String endpoint) {
		List<Station> stations = new ArrayList<Station>();
		
		if(localTest) {
			stations = factory_getStations();
		} else {
			String results = get(endpoint);
			// TODO
		}

		return stations;
	}
	
	public static void updateDBEmergency(String endpoint, List<Truck> trucks) {
		// TODO
	}
	
	/*
	 *  Fonctions "factory" utilisées quand localTest == true.
	 *  Permet de générer des Trucks / Fires / Stations.
	 *  C'est pour pouvoir tester que 
	 *  
	 *  On obtient alors cette "map" :
	 *  
	 *  	- - - - - - - - - -
	 *  	- S - - - T - - - -
	 * 		- - - - - - - - - -
	 *		- - - F - - - S - -
	 *		- T - - - - - - - -
	 *		- - - - - T - - T -
	 *		- - S - - - - - - -
	 *		- - - - - - - F - -
	 *		- T - - - F - - - -
	 *		- - - F - - - - - -
	 *
	 *		(0;0) -> haut gauche.
	 */

	private static List<Truck> factory_getTrucks() {
		List<Truck> trucks = new ArrayList<Truck>();
		trucks.add(new Truck(1, new Coord(5, 1), new Coord(1, 1), 1, 0));
		trucks.add(new Truck(2, new Coord(1, 4), new Coord(3, 3), 1, 1));
		trucks.add(new Truck(3, new Coord(5, 5), new Coord(7, 3), 2, 0));
		trucks.add(new Truck(4, new Coord(8, 5), new Coord(7, 7), 2, 2));
		trucks.add(new Truck(5, new Coord(1, 8), new Coord(2, 6), 3, 0));
		return trucks;
	}
	
	private static List<Fire> factory_getFires() {
		List<Fire> fires = new ArrayList<Fire>();
		fires.add(new Fire(1, new Coord(3, 3), (int) (Math.random() * 10 + 1)));
		fires.add(new Fire(2, new Coord(7, 7), (int) (Math.random() * 10 + 1)));
		fires.add(new Fire(3, new Coord(5, 8), (int) (Math.random() * 10 + 1)));
		fires.add(new Fire(4, new Coord(3, 9), (int) (Math.random() * 10 + 1)));
		return fires;
	}
	
	private static List<Station> factory_getStations() {
		List<Station> stations = new ArrayList<Station>();
		stations.add(new Station(1, new Coord(1, 1)));
		stations.add(new Station(2, new Coord(7, 3)));
		stations.add(new Station(3, new Coord(2, 6)));
		return stations;
	}
	
}