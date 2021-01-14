package utils;

public class Coord {

	private float x;
	private float y;
	
	public Coord(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public String toString() {
		return "(" + this.x + ";" + this.y + ")";
	}
	
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Coord coord = (Coord) obj;
        float delta = (float) 0.05;
        // Comparer à 0.05 permet d'occulter certains chiffres après la virgule.
        return (Math.abs(this.getX() - coord.getX()) < delta) && (Math.abs(this.getY() - coord.getY()) < delta);
    }

}
