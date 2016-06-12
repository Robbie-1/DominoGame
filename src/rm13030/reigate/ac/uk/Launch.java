package rm13030.reigate.ac.uk;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Launch {
	
	/**
	 * Arrays.toString(int[])
	 * 	-> Used to print integer arrays (i.e. Domino spot sets [domino.getDots()]);
	 * 
	 * If both players have the same highest double:
	 * 	-> Player 2 has "highest" double
	 * 
	 * -> If domino is a double, express it vertically, else, express it horizontally.
	 * 
	 * Format:
	 * -> Horizontal:
	 *		[x, y]
	 * -> Vertical:
	 * 		[x]
	 * 		[y]
	 * 
	 * 
	 */
	
	private static ArrayList<Domino> BONEYARD = new ArrayList<Domino>();
	private static ArrayList<Domino> GAME = new ArrayList<Domino>();
	private static int round = 0;
	private static Player player1;
	private static Player player2;
	private static Player currentPlayer;
	
	private Launch() {
		
		initBoneyard();
		
		setupPlayers();
		
		
		playGame(/*setupFrame()*/);
		
		//printDebug(player1);
		//printDebug(player2);
	}
	
	private void playGame(/*JFrame frame*/) {
		boolean play = true;
		round = 0;
		
		/*try {
			File file = new File("resources/domino-clipart-final-vertical.png");
			BufferedImage image = ImageIO.read(file);
			JLabel label = new JLabel(new ImageIcon(image));
			frame.getContentPane().add(label);
			topNumber = new Painter("~", new Font("Aliquam", Font.PLAIN, 20), Color.BLACK, image.createGraphics(), 75, 100);
			bottomNumber = new Painter("~", new Font("Aliquam", Font.PLAIN, 20), Color.BLACK, image.createGraphics(), 75, 300);
			frame.revalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		while (play) {
			currentPlayer = null;
			if (round == 0) {
				Object[] data = findHighestDouble(); //[0] = double value, [1] = Player
				//int highestDouble = (Integer) data[0];
				//Player doublePlayer = (Player) data[1];
						
				System.out.println("\n"+((Player) data[1]).getName()+" has the highest double ["+data[0]+", "+data[0]+"]\n");
				
				//printDomino((int) data[0], (int) data[0], true, false);
				//System.out.println(printDomino((int) data[0], (int) data[0], false, true));
				System.out.println("round="+round+";");
				printDominoes(((Player) data[1]).getDominos());
				round++;
				currentPlayer = (Player) data[1];
				takeTurn(currentPlayer);
			}
			
			if ((player1.getDominos().size() != 0) && ((player2.getDominos().size() != 0))) {
				//game still in progress
				takeTurn(currentPlayer);
			}
			
			play = false;
		}
	}
	
	private void takeTurn(Player p) {
		if (hasDouble(p)) {
			//place double, remove domino from their arraylist, then take another go
			System.out.println(p.getName()+" has double--");
			placeDouble(p);
			takeTurn(p);
		}
		currentPlayer = (currentPlayer == player1 ? player2 : player1);
		checkDominos(currentPlayer.getDominos(), GAME);
	}
	
	private void checkDominos(ArrayList<Domino> playerDominoes, ArrayList<Domino> gameDominoes) {
		for (int i=0; i<playerDominoes.size(); i++) {
			int dotX = gameDominoes.get(gameDominoes.size()-1).getDots()[0];
			if (playerDominoes.get(i).getDots()[0] == dotX) {
				GAME.add(playerDominoes.get(i));
				playerDominoes.remove(playerDominoes.get(i));
				printDominoes(GAME);
			}
		}
	}
	
	private void placeDouble(Player p) {
		Domino doubleDomino = null;
		for (int i=0; i<p.getDominos().size(); i++) {
			if (isDouble(p.getDominos().get(i).getDots())) {
				doubleDomino = p.getDominos().get(i);
				GAME.add(doubleDomino);
				p.getDominos().remove(doubleDomino);
			}
		}
		printDominoes(GAME);
	}
	
	private boolean hasDouble(Player p) {
		for (int i=0; i<p.getDominos().size(); i++) {
			if (isDouble(p.getDominos().get(i).getDots())) {
				return true;
			} continue;
		}
		return false;
	}
	
	private String printDomino(int dots1, int dots2, boolean horizontal, boolean breakLine) {
		String str = "";
		if (horizontal) {
			str+="["+dots1+", "+dots2+"]";
		} else {
			str+="["+dots1+"]\n"
			   + "["+dots2+"]";
		}
		return str;
	}
	
	private void printDominoes(ArrayList<Domino> dominoes) {
		for (int i=0; i<dominoes.size(); i++) {
			int dots[] = dominoes.get(i).getDots();
			if (dots[0] != dots[1]) {
				System.out.println(Arrays.toString(dominoes.get(i).getDots()));
			} else {
				System.out.println("\n["+dots[0]+"]\n"
						   	   + "["+dots[1]+"]");
			}
		}
	}
	
	/*private void printDomino2(int dots1, int dots2, boolean printHorizontal, boolean breakLine) {
	
	if (printHorizontal) {
		/**
		 * <*dots1:dots2*>
		 */
		/*System.out.println((breakLine ? "\n" : "") + "<*"+dots1+":"+dots2+"*>");
	/*} else {
		/**
		 * <*dots1*>
		 * <*dots2*>
		 */
		/*System.out.println((breakLine ? "\n" : "") + "<*"+dots1+"*>\n<*"+dots2+"*>");
	}
	
	}*/
	
	/*private JFrame setupFrame() {
		JFrame jF = new JFrame("Dominoes Game ~ Robbie McLeod");
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setSize(880, 600);
		jF.setVisible(true);
		return jF;
	}*/
	
	private Object[] findHighestDouble() {
		Object[] result = new Object[2]; //[0] = double value, [1] = player, [2] = Domino
		int highest_p1 = -1, highest_p2 = -1;
		
		for (int i=0; i<player1.getDominos().size(); i++) {
			int[] dots = player1.getDominos().get(i).getDots();
			if (isDouble(dots)) {
				if (dots[0] > highest_p1) {
					highest_p1 = dots[0];
				}
			}
		}
		
		for (int i=0; i<player2.getDominos().size(); i++) {
			int[] dots = player2.getDominos().get(i).getDots();
			if (isDouble(dots)) {
				if (dots[0] > highest_p2) {
					highest_p2 = dots[0];
				}
			}
		}
		
		if (((highest_p1 == -1) && (highest_p2 == -1) && (round == 0))) {
			//no double
			return handleNoDouble();
		}
		
		if (highest_p1 > highest_p2) {
			//p1 has highest double
			//System.out.println("[debug] "+player1.getName()+" has highest double :) ("+highest_p1+")");
			result[0] = highest_p1;
			result[1] = player1;
			return result;
		} else {
			//p2 has highest double
			//System.out.println("[debug] "+player2.getName()+" has highest double :) ("+highest_p2+")");
			result[0] = highest_p2;
			result[1] = player2;
			return result;
		}
	}
	
	private Object[] handleNoDouble() {
		boolean noDouble = true;
		int foundDouble = -1;
		Player p = null;
		while (noDouble) {
			Domino gen = Domino.generateRandom();
			player1.getDominos().add(gen);
			if (isDouble(gen.getDots())) {
				foundDouble = gen.getDots()[0];
				p = player1;
				noDouble = false;
			}
			
			Domino gen2 = Domino.generateRandom();
			player2.getDominos().add(gen);
			if (isDouble(gen2.getDots())) {
				foundDouble = gen2.getDots()[0];
				p = player2;
				noDouble = false;
			}
		}
		System.out.println("generated a double: "+foundDouble+", found by: " + p.getName());
		Object[] o = {foundDouble, p};
		return o;
	}
	
	private boolean isDouble(int[] dots) {
		if (dots[0] == dots[1]) {
			return true;
		} else {
			return false;
		}
	}
	
	private void setupPlayers() {
		Scanner sc = new Scanner(System.in);
		System.out.println("== DOMINO GAME ==");
		for (int i=0; i<2; i++) {
			System.out.println("--> Please enter the name of "+(i==0 ? "the first player" : "the second player")+":");
			String name = sc.next();
			if (player1 == null) {
				player1 = new Player(name, new ArrayList<Domino>());
				player1.pullPlayerTiles();
			} else {
				player2 = new Player(name, new ArrayList<Domino>());
				player2.pullPlayerTiles();
			}
		}
		sc.close(); //prevents potential memory leak
	}
	
	/**
	 * Generates 28 random tiles, and adds to <GAME_DOMINOS>
	 */
	private static void initBoneyard() {
		for (int i=0; i < 28; i++) {
			BONEYARD.add(Domino.generateRandom());
		}
	}
	
	/**
	 * DEBUGGING
	 */
	private static void printDebug(Player p) {
		System.out.println("== Player: "+p.getName()+" ==");
		for (int i=0; i<p.getDominos().size(); i++) {
			System.out.println(Arrays.toString(p.getDominos().get(i).getDots()));
		}
		System.out.println("== [tiles: "+p.getDominos().size()+"] ==");
		
		/*System.out.println("\\BONEYARD_DOMINOS [size: "+BONEYARD.size()+"]\\:\n");
		for (int i=0; i < BONEYARD.size(); i++) {
			System.out.println(Arrays.toString(BONEYARD.get(i).getDots()));
		}*/
	}
	
	public static ArrayList<Domino> getBoneyard() {
		return BONEYARD;
	}

	public static void setBoneyard(ArrayList<Domino> boneyard) {
		BONEYARD = boneyard;
	}

	public static Player getPlayer1() {
		return player1;
	}

	public static Player getPlayer2() {
		return player2;
	}
	
	public static void main(String args[]) { new Launch(); }

}
