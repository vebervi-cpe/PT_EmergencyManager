package model;

import java.util.ArrayList;
import java.util.List;

import utils.httpRequester;

public class Emergency {
	
	private List<Truck> trucks;
	private List<Fire> fires;
	private List<Station> stations;
	
	private List<Truck> trucksToAssign = new ArrayList<Truck>();
	private List<Fire> firesNotHandled = new ArrayList<Fire>();
	
	private String serverDBEmergency;
	
	public Emergency(String serverEmergency) {
		this.serverDBEmergency = serverEmergency;
	}
	
	public void run() {
		while(true) {
			// Etape 0 : pause de 10 secondes.
			this.step0_wait(10);
			
			System.out.print("<====> START OF THE LOOP <====>\n");
			
			// Etape 1 : récupérer les feux, les camions et les casernes de la BDD Emergency.
			this.trucks = httpRequester.getAllTrucks(this.serverDBEmergency);
			this.fires = httpRequester.getAllFires(this.serverDBEmergency);
			this.stations = httpRequester.getAllStations(this.serverDBEmergency);
			
			System.out.print("<====> TRUCKS GET <====>\n" + this.trucks + "\n");
			System.out.print("<====> STATIONS GET <====>\n" + this.stations + "\n");
			
			// Etape 2 : on détermine les feux qui ne sont pas encore traités.
			this.step2_computeFires();
			
			System.out.print("<====> FIRES GET <====>\n" + this.fires + "\n");
			
			// Etape 3 : on détermine les camions que l'on pourra assigner.
			this.step3_computeTrucks();
			
			// Etape 4 : on assigne finalement les camions selons les disponibilités des feux.
			this.step4_assignTrucks();
			
			System.out.print("<====> TRUCKS TO UPDATE <====>\n" + this.trucksToAssign + "\n\n");
			
			System.out.print("<====> HTTP REQUESTS <====>\n");
			
			// Etape 5 : mettre à jour la BDD Emergency (camions).
			httpRequester.updateDBEmergency(this.serverDBEmergency, this.trucksToAssign);
			
			System.out.print("<====> END OF THE LOOP <====>\n\n");
			
			this.clearLists();
		}
	}
	
	private void step0_wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void step2_computeFires() {
		this.firesNotHandled.addAll(this.fires); // On commence par copier tous les feux dans ceux non traités.
		for(Fire fire : this.fires) {
			fire.setIsHandled(false); // On considère qu'aucun feu n'est traité par défaut (aucun camion n'est sur place ou s'y rend).
			for(Truck truck : this.trucks) {
				// Si un camion a pour idFire celui du feu, c'est que le feu est traité.
				if(truck.getIdFire() == fire.getId()) {
					// On change donc la propriété isHandled et on l'enlève de la liste des feux pas encore traités.
					fire.setIsHandled(true);
					this.firesNotHandled.remove(fire);
					break;
				}
			}
		}
	}
	
	private void step3_computeTrucks() {
		for(Truck truck : this.trucks) {
			Boolean hasFire = false;
			
			// On parcours tous les feux...
			for(Fire fire : this.fires) {
				// ...et si notre camion est lié à un feu que l'on a récupéré, c'est qu'il n'est pas à assigner.
				if(truck.getIdFire() == fire.getId()) {
					hasFire = true;
				}
			}
			
			// Si le camion n'est lié à aucun feu, on va devoir l'assigner.
			if(!hasFire) {
				// On ajoute ce camion à la liste de ceux qu'il faudra assigner.
				this.trucksToAssign.add(truck);
				truck.setIdFire(0);
			}
		}
	}
	
	private void step4_assignTrucks() {
		// Liste des camions qu'il faudra enlever de trucksToUpdate.
		// (car ils n'ont pas pu être assignés à un feu mais retournent déjà à leur caserne).
		List<Truck> trucksNotToUpdate = new ArrayList<Truck>();

		for(Truck truck : this.trucksToAssign) {
			// On va assigner chaque camion disponible au premier feu disponible (à améliorer - TODO).
			for(Fire fire : this.firesNotHandled) {
				truck.setIdFire(fire.getId());
				fire.setIsHandled(true);
				this.firesNotHandled.remove(fire);
				break;
			}
			
			// Si le camion n'a toujours pas été assigné à un feu (aucun feu disponible).
			if(truck.getIdFire() == 0) {
				// On l'ajoute à la liste des camions à ne pas mettre à jour.
				trucksNotToUpdate.add(truck);
			}
		}
		
		for(Truck truck : trucksNotToUpdate) {
			this.trucksToAssign.remove(truck);
		}
	}
	
	private void clearLists() {
		this.trucks.clear();
		this.fires.clear();
		this.stations.clear();
		this.trucksToAssign.clear();
		this.firesNotHandled.clear();
	}

}
