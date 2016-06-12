package rm13030.reigate.ac.uk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Utils {
	
	/**
	 * A collection of useful utilities
	 */
	
	public static boolean isSet(ArrayList<?> list, Integer index) {
		   return index != null && index >=0 && index < list.size() && list.get(index) != null;
	}
	
	public static void paint(String str, Graphics g, int dimX, int dimY) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Aliquam", Font.PLAIN, 20));
        g2.setColor(Color.gray);
        g2.drawString(str, dimX, dimY);
	}
	
}
