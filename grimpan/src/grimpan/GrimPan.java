package grimpan;

import java.awt.Color;

import javax.swing.JFrame;

class GrimPanel extends MyUtil{
	public GrimPanel() {

	}
	
//	@Override
//	public void keyPressed(KeyEvent e) {
//		System.out.println(e.getKeyChar());
//		if(e.isShiftDown() == true) {
//			
//		}
//	}
}

public class GrimPan extends JFrame{
	private GrimPanel panel = new GrimPanel();
	
	public GrimPan() {
		setLayout(null);
		setBounds(100,100,700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(new Color(119, 172, 241));
		setVisible(true);
		revalidate();
		add(panel);
	}
}
