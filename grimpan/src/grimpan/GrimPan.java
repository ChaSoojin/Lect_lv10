package grimpan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

class Xy {
	private int x;
	private int y;
	
	public Xy(int x, int y) {
		this.x = x;
		this.y = y;
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
}

class GrimPanel extends MyUtil{
	private int x1, y1; //????
	private int x2, y2; //??
	private boolean pressShift = false;
	private ArrayList<Xy> xyList = new ArrayList<>();
	
	public GrimPanel() {
		setLayout(null);
		setBounds(0,0,700,500);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setFocusable(true);
		addKeyListener(this);
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
////		System.exit(0);
//	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
//		int drawX = Math.min(this.x1, this.x2);
//		int drawY = Math.min(this.y1, this.y2);
//		int drawWidth = Math.abs(this.x1-this.x2);
//		int drawHeight = Math.abs(this.y1-this.y2);
		
		int drawX = 0, drawY = 0, drawWidth = 0, drawHeight = 0;
		int x = 0, y = 0, x2 = 0, y2 = 0;
		
		if(this.xyList.size() > 1) {
			for(int i = 0; i < this.xyList.size()-1; i++) {
				x = this.xyList.get(i).getX();
				y = this.xyList.get(i).getY();
				
				x2 = this.xyList.get(i+1).getX(); 
				y2 = this.xyList.get(i+1).getY(); 
				
				drawX = Math.min(x, x2); 
				drawY = Math.min(y, y2); 
				drawWidth = Math.abs(x-x2);
				drawHeight = Math.abs(y-y2);
				
				g.drawRect(drawX, drawY, drawWidth, drawHeight);
			}			
		}
		
		if(this.pressShift) {
			drawX = drawY;
			drawWidth = drawHeight;
		}
		repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		this.x1 = e.getX();
		this.y1 = e.getY();
		this.xyList.add(new Xy(this.x1, this.y1));
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);	
		this.x2 = e.getX();
		this.y2 = e.getY();		
		this.xyList.add(new Xy(this.x2, this.y2));
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.isShiftDown() == true) {
			this.pressShift = true;
		}		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		this.pressShift = false;
	}
}

public class GrimPan extends JFrame implements MouseListener{
	private GrimPanel panel = new GrimPanel();
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public final int W = dm.width;
	public final int H = dm.height;
	private JButton close = new JButton();
	
	public GrimPan() {
		setLayout(null);
		setBounds(W/2 - 700/2, H/2 - 700/2, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(new Color(119, 172, 241));
		addCloseButton();
		add(panel);
		setVisible(true);
		revalidate();
	}
	
	private void addCloseButton() {
		this.close.setBounds(500, 550, 100, 30);
		this.close.setText("CLOSE");
		this.close.addMouseListener(this);
		add(this.close);	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if((JButton)e.getSource() == this.close) {
			this.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
