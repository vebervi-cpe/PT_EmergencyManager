package model;

import utils.Coord;

public class Fire {
	
	private int id;
	private Coord position;
	private int intensity;

	private Boolean isHandled;
	
	public Fire(int id, Coord position, int intensity) {
		this.id = id;
		this.position = position;
		this.intensity = intensity;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Coord getPosition() {
		return this.position;
	}
	
	public int getIntensity() {
		return this.intensity;
	}
	
	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}
	
	public Boolean getIsHandled() {
		return this.isHandled;
	}
	
	public void setIsHandled(Boolean isHandled) {
		this.isHandled = isHandled;
	}
	
	public String toString() {
		return "Fire #" + this.getId() +
				"\n  Position : " + this.getPosition() +
				"\n  Intensity : " + this.getIntensity() + 
				"\n  IsHandled : " + this.getIsHandled() + "\n";
	}
	
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Fire fire = (Fire) obj;
        return this.getId() == fire.getId();
    }
	
}
