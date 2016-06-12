package rm13030.reigate.ac.uk;

import java.util.Random;

public class Domino {
	
	private static final int[] VALUES = {0, 1, 2, 3, 4, 5, 6};
	
	private int[] dots;
	
	public Domino(int[] dots) {
		this.dots = dots;
	}

	public int[] getDots() {
		return dots;
	}

	public void setDots(int[] dots) {
		this.dots = dots;
	}
	
	public static Domino generateRandom() {
		Random r = new Random();
		int[] dots = {r.nextInt(VALUES[6]+1), r.nextInt(VALUES[6]+1)};
		return new Domino(dots);
	}
	
}
