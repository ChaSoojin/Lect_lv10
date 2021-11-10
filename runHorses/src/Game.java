import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import models.Horse;

class GamePanel extends MyUtil{
	private ArrayList<Horse> horses = new ArrayList<>();
	
	public GamePanel() {
		setLayout(null);
		setBounds(0,0,700,700);		
		setHorses();
	}
	
	private void setHorses() {
		JLabel imgLabel = new JLabel();
		String fileName = "C:\\Users\\a\\Desktop\\image\\horse1.jpg";
		ImageIcon icon1 = new ImageIcon(fileName);
		horses.add(new Horse(1, fileName, icon1));
		
		imgLabel.setIcon(icon1);
		imgLabel.setBounds(30,30,30,30);
		setVisible(true);
	}	
}

public class Game extends JFrame{
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public final int W = dm.width;
	public final int H = dm.height;
	private GamePanel content = new GamePanel();
	
	public Game() {
		setLayout(null);
		setBounds(W/2 - 700/2, H/2 - 700/2, 700, 700);
		setBackground(new Color(119, 172, 241));
		add(content);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		revalidate();
	}
}
