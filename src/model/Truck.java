package model;

import utils.Coord;

public class Truck {

	private int id;
	private Coord position;
	private int idStation;
	private int idFire;
	
	public Truck(int id, Coord position, int idStation, int idFire) {
		this.id = id;
		this.position = position;
		this.idStation = idStation;
		this.idFire = idFire;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Coord getPosition() {
		return this.position;
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
