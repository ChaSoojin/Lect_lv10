package controller;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

import racemodels.Horse;
import racemodels.MyUtil;
import racemodels.Race;

public class Racing extends MyUtil implements Runnable{
	private Random random = new Random();
	private int ms;
	private boolean isRun;
	private JLabel timer = new JLabel("ready");
	private JButton reset = new JButton("start");
	private final int SIZE = 5;
	private Horse[] horses = new Horse[SIZE];
	private int rank;
	
	public Racing() {
		setLayout(null);
		setBounds(0,0,Race.WIDTH, Race.HEIGHT);	
		setTimer();
		setButton();
		setGame();
	}
	
	private void setGame() {
		this.rank = 1;
		this.ms = 0;
		int x = 30, y = 120;
		this.timer.setText("ready");
		
		for(int i = 0; i < SIZE; i++) {
			this.horses[i] = new Horse(i+1, x, y, 120, 90, String.format("images/horse%d.png", i+1));
			y += 100;
		}
	}

	private void setButton() {
		this.reset.setBounds(30,50,100,50);
		this.reset.addActionListener(this);
		add(this.reset);
	}

	private void setTimer() {
		timer.setBounds(135,50,100,50);
		add(this.timer);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton)e.getSource();
			
			if(target == this.reset) {
				this.isRun = this.isRun == true ? false : true;
				this.reset.setText(this.isRun ? "reset" : "start");
				
				if(!this.isRun) {
					setGame();
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i < SIZE; i++) {
			Horse h = this.horses[i];
			//타입이 imageicon이기때문에 getimage()로 가져와주어야 함
			g.drawImage(h.getImage().getImage(), h.getX(), h.getY(), null);
			
			//트랙
			g.drawLine(30,h.getY() + h.getH(), Race.WIDTH-30,h.getY() + h.getH());
			
			//rank
			if(h.getState() == h.GOAL) {
				//기록
				g.setFont(new Font("", Font.PLAIN, 10));
				g.drawString(h.getRecord(), Race.WIDTH-250, h.getY() + h.getH()/2);
				
				//등수
				g.setFont(new Font("", Font.BOLD, 20));
				g.drawString(h.getRank() + "등", Race.WIDTH-280, h.getY() + h.getH()/2);
			}
		}
		
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		update();
		repaint();
	}
	
	//말좌표 업데이트
	private void update() {
		boolean goal = false;
		
		if(this.isRun) {
			for(int i = 0; i < SIZE; i++) {
				Horse h = this.horses[i];
				int jump = random.nextInt(10) * 3;
				int tempX = h.getX() + jump;
				
				if(h.getState() == h.RUN) {
					
					//goal
					if(tempX >= Race.WIDTH-200 && !goal) {
						h.setState(h.GOAL);
						h.setRecord(String.format("%4d.%3d", this.ms/1000, this.ms%1000));
						h.setRank(this.rank++);
						goal = !goal;
					}
					else if(tempX >= Race.WIDTH-200) {
						i--;
						continue;
					}
						
					h.setX(tempX);					
				}
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			if(isRun) {
				this.ms++;
				this.timer.setText(String.format("%3d.%3d", this.ms/1000, this.ms%1000));
			}
			
			try {
				Thread.sleep(1);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}