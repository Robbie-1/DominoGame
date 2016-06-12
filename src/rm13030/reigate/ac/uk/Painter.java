package rm13030.reigate.ac.uk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Painter {
	
	private String str;
	private Font f;
	private Color c;
	private Graphics g;
    final Graphics2D g2;
	private int dimX;
	private int dimY;
	
	public Painter(String str, Font f, Color c, Graphics g, int dimX, int dimY) {
        g2 = (Graphics2D) g;
        g2.setFont(f);
        g2.setColor(c);
        g2.drawString(str, dimX, dimY);
	}

	public String getString() {
		return str;
	}

	public void setString(String str) {
		this.str = str;
	}
	
	public Font getFont() {
		return f;
	}

	public void setFont(Font f) {
		this.f = f;
	}

	public Color getColour() {
		return c;
	}

	public void setColour(Color c) {
		this.c = c;
	}

	public Graphics getGraphics() {
		return g;
	}

	public void setGraphics(Graphics g) {
		this.g = g;
	}

	public int getDimX() {
		return dimX;
	}

	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public void setDimY(int dimY) {
		this.dimY = dimY;
	}
	
	

}
