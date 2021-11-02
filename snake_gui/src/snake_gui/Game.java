package snake_gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class SnakeAlertFrame extends JFrame{
	JLabel text = new JLabel();
	public final int W = Game.dm.width;
	public final int H = Game.dm.height;
	
	public SnakeAlertFrame(int size) {
		setLayout(null);
		setBounds(W/2 - 120, H/2 - 120, 300, 200);
		setVisible(true);
		
		this.text.setBounds(0, 0, 280, 200);
		this.text.setText("[GameOver] 꼬리길이: " + String.format("%d", size));
		this.text.setHorizontalAlignment(JLabel.CENTER);
		this.text.setVisible(true);
		add(this.text);
	}
}

class SnakePanel extends MyUtil{
	private Rect[][] map;
	private Rect[][] snake;
	private Rect apple;
	private int SIZE = 4; //10까지만 증가
	private boolean keyPress;
	private int tmpY = 0, tmpX = 0; //마지막꼬리좌표저장
	private int chk = 0; //게임오버체크
	private JButton reset = new JButton();
	
	public SnakePanel() {
		setLayout(null);
		setBounds(50, 50, 700, 700);
		setHeader();
		setMap();
		setSnake();
		setApple();
		setFocusable(true);
		addKeyListener(this);
		addResetButton();
	}

	private void addResetButton() {
		this.reset.setBounds(450, 10, 100, 30);
		this.reset.setText("RESET");
		this.reset.addActionListener(this);
		add(this.reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		
		if(target == this.reset) {
			this.SIZE = 4;
			this.tmpY = 0;
			this.tmpX = 0;
			this.chk = 0;
			setMap();
			setSnake();
			setApple();			
		}
	}
	
	private void setApple() {
		Random ran = new Random();
		int y = 0, x = 0;
		
		while(true) {
			Rect mapRect = this.map[9][9];
			y = ran.nextInt(10) + 1;
			x = ran.nextInt(10) + 1;

			boolean chk = true;
			for(int i = 0; i < this.SIZE; i++) {
				Rect rect = this.snake[0][i];
				if(y*50 == rect.getY() && x*50 == rect.getX()) chk = false;
			}
			
			if(chk) break;
		}
		
		this.apple = new Rect(x*50,y*50,50,50);
	}

	private void setHeader() {
		JLabel head = new JLabel("SNAKE GAME");
		head.setBounds(90,0,400,170);
		head.setFont(new Font("", Font.BOLD, 20));
		head.setVerticalAlignment(JLabel.NORTH);
		head.setHorizontalAlignment(JLabel.CENTER);
		add(head);
	}
	
	private void setMap() {
		this.map = new Rect[10][10];
		
		int x = 600 / 2 - 50 * 10 / 2;
		int y = 600 / 2 - 50 * 10 / 2;
		
		for(int i = 0; i < this.map.length; i++) {
			for(int j = 0; j < this.map[i].length; j++) {
				this.map[i][j] = new Rect(x, y, 50, 50);
				x += 50;
			}
			x = 600 / 2 - 50 * 10 / 2;
			y += 50;
		}
	}

	private void setSnake() {
		this.snake = new Rect[1][this.SIZE];
		
		int x = 600 / 2 - 50 * 10 / 2;
		int y = 600 / 2 - 50 * 10 / 2;
		
		for(int i = 0; i < this.SIZE; i++) {
			this.snake[0][i] = new Rect(x, y, 50, 50);
			x += 50;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//맵그리기
		for(int i = 0; i < this.map.length; i++) {
			for(int j = 0; j < this.map[i].length; j++) {
				Rect rect = this.map[i][j];
				g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
			}
		}
		
		//뱀
		Rect head = this.snake[0][0];
		head.setColor(Color.black);
		g.setColor(head.getColor());
		g.fillRect(head.getX(), head.getY(), head.getWidth(), head.getHeight());
		
		for(int i = 1; i < this.SIZE; i++) {
			Rect rect = this.snake[0][i];
			rect.setColor(Color.green);
			g.setColor(rect.getColor());
			g.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
		
		//사과
		Rect appleRect = this.apple;
		appleRect.setColor(Color.red);
		g.setColor(appleRect.getColor());		
		g.fillRect(appleRect.getX(), appleRect.getY(), appleRect.getWidth(), appleRect.getHeight());
		
		repaint();
	}
	
	private void snakeMove(int y, int x) {
		Rect last = this.snake[0][this.SIZE-1];
		this.tmpY = last.getY();
		this.tmpX = last.getX();

		for(int i = this.SIZE-1; i > 1; i--) {
			Rect rect = this.snake[0][i];
			Rect rect2 = this.snake[0][i-1];
			rect.setY(rect2.getY());
			rect.setX(rect2.getX());
		}
		
		Rect rect = this.snake[0][1];
		rect.setY(y);
		rect.setX(x);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!this.keyPress) {
			this.keyPress = true;
			
			Rect rect = this.snake[0][0];
			
			int y = rect.getY();
			int x = rect.getX();
			
			if(checkBoundary(y,x,e.getKeyCode())) {
				switch(e.getKeyCode()) {
				case 38:
					chkCollision(y-50,x);
					rect.setY(rect.getY()-50);
					break;
				case 37:
					chkCollision(y,x-50);
					rect.setX(rect.getX()-50);
					break;
				case 40:
					chkCollision(y+50,x);
					rect.setY(rect.getY()+50);
					break;
				case 39:
					chkCollision(y,x+50);
					rect.setX(rect.getX()+50);
					break;
				}
				snakeMove(y,x);
				checkGetItem();
			}
		}		
	}
	
	private void checkGameOver() {
		if(this.chk == -1) {
			new SnakeAlertFrame(this.SIZE);
		}
	}

	private void checkGetItem() {
		Rect head = this.snake[0][0];
		Rect appleRect = this.apple;

		if(head.getY() == appleRect.getY() && head.getX() == appleRect.getX()) {
			setApple();
			setSnakeSize();
		}
	}

	private void setSnakeSize() {
		if(this.SIZE < 10) {
			this.SIZE++;
			addSnakeTail();			
		}
	}

	private void addSnakeTail() {
		Rect[][] tmp = this.snake;
		this.snake = new Rect[1][this.SIZE];
		
		for(int i = 0; i < this.SIZE-1; i++) {
			this.snake[0][i] = new Rect(tmp[0][i].getX(), tmp[0][i].getY(), tmp[0][i].getWidth(), tmp[0][i].getHeight());
		}
		
		this.snake[0][this.SIZE-1] = new Rect(this.tmpX, this.tmpY, 50, 50);
	}

	private void chkCollision(int y, int x) {
		for(int i = 2; i < this.SIZE; i++) {
			Rect myRect = this.snake[0][i];
			if(myRect.getX() == x && myRect.getY() == y) this.chk = -1;
		}
	}
	
	private boolean checkBoundary(int y, int x, int code) {
		if(code >= 37 && code <= 40) {
			Rect mapRect = this.map[0][0];	
			Rect mapRect2 = this.map[9][9];
			Rect myRect = this.snake[0][1];
			
			if(code == 37) {
				if(x - 50 < mapRect.getX()) this.chk = -1;
				if(x - 50 < mapRect.getX() || x - 50 == myRect.getX()) {
					return false; 
				}
			}
			else if(code == 38) {
				if(y - 50 < mapRect.getY()) this.chk = -1;
				if(y - 50 < mapRect.getY() || y - 50 == myRect.getY()) {
					return false;
				}
			}
			else if(code == 39) {
				if(x + 50 > mapRect2.getX()) this.chk = -1;
				if(x + 50 > mapRect2.getX() || x + 50 == myRect.getX()) {
					return false;
				}
			}
			else if(code == 40) {
				if(y + 50 > mapRect2.getY()) this.chk = -1;
				if(y + 50 > mapRect2.getY() || y + 50 == myRect.getY()) {
					return false;
				}
			}
		}
		else return false;
		return true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keyPress = false;
		checkGameOver();
	}
}

public class Game extends JFrame {
	private SnakePanel panel = new SnakePanel();
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public final int W = dm.width;
	public final int H = dm.height;
	
	public Game() {
		super("Snake Game");
		setLayout(null);
		setBounds(W/2 - 700/2, H/2 - 700/2, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
		add(panel);
	}
}
