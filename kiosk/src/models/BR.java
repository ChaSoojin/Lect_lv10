package models;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BR {
	private int num;
	private int x,y,w,h;
	private String fileName;
	private ImageIcon image;
	
	public BR(int num, int x, int y, int w, int h) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		if(this.num <= 12) {
			this.fileName = String.format("brimages/icecream%d.png", this.num);
		}
		else {
			this.fileName = String.format("brimages/icecreamcake%d.png", this.num-12);
		}
		
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(this.w, this.h, Image.SCALE_SMOOTH);
		this.image = new ImageIcon(temp);			
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public String getFileName() {
		return fileName;
	}

	public ImageIcon getImage() {
		return image;
	}
}
