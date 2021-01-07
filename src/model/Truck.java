package model;

import utils.Coord;

public class Truck {

	private int id;
	private Coord position;
	private Coord destination;
	private int idStation;
	private int idFire;
	
	public Truck(int id, Coord position, Coord destination, int idStation, int idFire) {
		this.id = id;
		this.position = position;
		this.destination = destination;
		this.idStation = idStation;
		this.idFire = idFire;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Coord getPosition() {
		return this.position;
	}
	
	public Coord getDestination() {
		return this.destination;
	}
	
	public void setDestination(Coord destination) {
		this.destination = destination;
	}
	
	public int getIdStation() {
		return this.idStation;
	}
	
	public int getIdFire() {
		return this.idFire;
	}
	
	public void setIdFire(int idFire) {
		this.idFire = idFire;
	}
	
	public String toString() {
		return "Truck #" + this.getId() +
				"\n  Position : " + this.getPosition() +
				"\n  Destination : " + this.getDestination() +
				"\n  IDStation : " + this.getIdStation() +
				"\n  IDFire : " + this.getIdFire() + "\n";
	}
	
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Truck truck = (Truck) obj;
        return this.getId() == truck.getId();
    }
	
}
