package rm13030.reigate.ac.uk;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	private String name;
	private ArrayList<Domino> PLAYER_DOMINOS = new ArrayList<Domino>();
	
	/**
	 * Pulls 7 tiles randomly, then adds to <PLAYER_DOMINOS>
	 */
	public void pullPlayerTiles() {
		Random r = new Random();
		for (int i=0; i < 7; i++) {
			int random = r.nextInt(Launch.getBoneyard().size());
			//System.out.println("[debug] random = "+random);
			//System.out.println("[debug] index"+random+" in BONEYARD = "+Arrays.toString(Launch.BONEYARD.get(random).getDots()));
			if (Utils.isSet(Launch.getBoneyard(), random)) {
				PLAYER_DOMINOS.add(Launch.getBoneyard().get(random));
				Launch.getBoneyard().remove(random);
			}
		}
	}
	
	public Player(String name, ArrayList<Domino> dominos) {
		this.name = name;
		this.PLAYER_DOMINOS = dominos;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Domino> getDominos() {
		return PLAYER_DOMINOS;
	}
	
	public void setDominos(ArrayList<Domino> dominos) {
		this.PLAYER_DOMINOS = dominos;
	}
	
}
