package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import models.BR;
import models.BrKioskFrame;
import models.GuiUtil;

class PayPanel extends GuiUtil{
	private JButton result = new JButton();
	private ImageIcon payimage;
	
	public PayPanel() {
		setLayout(null);
		setBounds(140, 100, 500, 500);
		setImage();
		setResultBtn();
	}
	
	private void setImage() {
		String fileName = String.format("brimages/text_info.png");
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		this.payimage = new ImageIcon(temp);
	}
	
	private void setResultBtn() {
		this.result.setBounds(40,210,120,30);
		this.result.setText("�����Ϸ�");
		this.result.addActionListener(this);
		add(this.result);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(this.payimage.getImage(), 0, 0, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.result) {
			JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
			System.exit(0);
		}
	}
}

class BRAlertFrame extends JFrame{
	private PayPanel panel = new PayPanel();
	
	public BRAlertFrame() {
		setLayout(null);
		setBounds(400, 150, 500, 500);
		add(panel);
		setVisible(true);
		revalidate();
	}
}

public class BrKiosk extends GuiUtil{
	private int SIZE = 15;
	private BR[] menu = new BR[SIZE];
	private JButton[] menuBtn = new JButton[SIZE];
	private JButton[] sizeBtn = new JButton[4]; //���̽�ũ�� ������
	private JButton payBtn = new JButton();
	
	private String[] productArr = new String[SIZE]; //���̽�ũ�� �޴��̸�
	private boolean[] menuSize = new boolean[4]; //���̽�ũ�� ������ ��������
	private int[] productCnt = new int[4]; // 3,4,5,6������
	private int[] priceList = new int[4];
	private int[] cakePriceList = new int[3];

	private int totalCnt = 0, cakeCnt = 0;
	private int idx = -1, cakeIdx = -1;
	
	private ImageIcon logoimage;
	private ImageIcon[] sizeImage = new ImageIcon[4];
	
	private JTable table = null;
	private JTable resultTable = null;
	private Vector<String> colName = new Vector<>();
	private Vector<Vector<String>> products = new Vector<>();
	
	public BrKiosk() {
		setLayout(null);
		setBounds(0,0,BrKioskFrame.WIDTH, BrKioskFrame.HEIGHT);	
		setHeader();
		setMenu();
		setMenuButton();
		setTable();
		setPayBtn();
	}

	private void setHeader() {
		JLabel head = new JLabel("Baskin Robbins");
		head.setBounds(30,5,400,170);
		head.setFont(new Font("���� ���", Font.BOLD, 40));
		head.setForeground(Color.BLUE);
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setVerticalAlignment(JLabel.CENTER);
		add(head);

		String fileName = String.format("brimages/logo.png");
		Image temp = new ImageIcon(fileName).getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
		this.logoimage = new ImageIcon(temp);
	}

	private void setMenu() {
		int x = 30, y = 120;
		
		for(int i = 0; i < SIZE; i++) {
			this.menu[i] = new BR(i+1, x, y, 120, 90);
			x += 120;
			
			if(i % 5 == 4) {
				y += 150;
				x = 30;
			}
		}
		
		productSetting();
		sizeSetting();
		priceSetting();
	}
	
	private void sizeSetting() {
		for(int i = 0; i < 4; i++) {
			String fileName = String.format("brimages/size%d.png", (i+1));
			Image temp = new ImageIcon(fileName).getImage().getScaledInstance(130,100, Image.SCALE_SMOOTH);
			sizeImage[i] = new ImageIcon(temp);
		}
		
		JLabel sizelabel = new JLabel("������ ����");
		sizelabel.setBounds(570,5,400,170);
		sizelabel.setFont(new Font("���� ���", Font.BOLD, 20));
		sizelabel.setForeground(Color.PINK);
		sizelabel.setHorizontalAlignment(JLabel.CENTER);
		sizelabel.setVerticalAlignment(JLabel.CENTER);
		add(sizelabel);
		
		int x = 650, y = 220;
		
		for(int i = 0; i < 4; i++) {
			this.sizeBtn[i] = new JButton();
			this.sizeBtn[i].setBounds(x,y,120,20);
			this.sizeBtn[i].addActionListener(this);
			add(this.sizeBtn[i]);	
			x += 130;
			
			if(i % 2 == 1) {
				x = 650;
				y += 130;
			}
		}
		
		this.sizeBtn[0].setText("3������");
		this.sizeBtn[1].setText("4������");
		this.sizeBtn[2].setText("5������");
		this.sizeBtn[3].setText("6������");
	}

	private void productSetting() {
		productArr[0] = "�̻��� ������ �ػ���";
		productArr[1] = "ü�� �����";
		productArr[2] = "���� �ʰ�";
		productArr[3] = "���ݸ�";
		productArr[4] = "������ ��Ű��ũ��";
		productArr[5] = "���� ġ������ũ";
		productArr[6] = "���ý�Ÿ"; 
		productArr[7] = "������ �ܰ���";
		productArr[8] = "�Ƹ�� ������";
		productArr[9] = "���κ��� ����Ʈ";
		productArr[10] = "��Ʈ ���ݸ�Ĩ";
		productArr[11] = "�������� ��Ʈ�κ���";
		productArr[12] = "���Դ� �����";
		productArr[13] = "��ũ�� ����ġ";
		productArr[14] = "������ ��������";
		
		cakePriceList[0] = 26000;
		cakePriceList[1] = 28000;
		cakePriceList[2] = 26000;		
	}
	
	private void priceSetting() {
		productCnt[0] = 3;
		productCnt[1] = 4;
		productCnt[2] = 5;
		productCnt[3] = 6;
		
		priceList[0] = 8200; //����Ʈ
		priceList[1] = 15500; //����
		priceList[2] = 22000; //�йи�
		priceList[3] = 26500; //��������
	}
	
	private void setTable() {
		//JTable(Vector<?>, Vector<?>)
		//1) �ǵ�����
		//2) �÷��̸�
		
		this.colName.add("�ֹ��Ѿ�");
		this.colName.add("�ֹ�����");
		
		this.table = new JTable(this.products, this.colName);
		this.table.setBounds(650, 400, 300,95);
		this.table.setGridColor(Color.black);
		this.table.setBorder(new LineBorder(Color.black));
		add(this.table);
		
//		JScrollPane js = new JScrollPane(this.table);
//		js.setBounds(650, 400, 300,95);
//		js.setAutoscrolls(true);
//		add(js);
		
		setResultTable();
	}
	
	private void setResultTable() {	
		this.resultTable = new JTable(1,2);
		this.resultTable.setTableHeader(table.getTableHeader());
		this.resultTable.setBounds(650, 500, 300, 50);
		this.resultTable.setGridColor(Color.black);
		this.resultTable.setBorder(new LineBorder(Color.black));
		add(this.resultTable);
	
		JScrollPane js = new JScrollPane(this.resultTable);
		js.setBounds(650, 510, 300, 41);
		js.setAutoscrolls(true);
		add(js);
		
		this.resultTable.revalidate();
		this.resultTable.repaint();
	}

	private void setPayBtn() {
		this.payBtn.setBounds(830,560,120,30);
		this.payBtn.setText("�����ϱ�");
		this.payBtn.addActionListener(this);
		add(this.payBtn);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(this.logoimage.getImage(), 30, 60, this);
		
		for(int i = 0; i < SIZE; i++) {
			BR br = this.menu[i];
			g.drawImage(br.getImage().getImage(), br.getX(), br.getY(), this);
		}
		
		int x = 650, y = 120;
		
		for(int i = 0; i < 4; i++) {
			g.drawImage(sizeImage[i].getImage(), x, y, this);
			x += 120;
			
			if(i % 2 == 1) {
				x = 650;
				y += 120;
			}
		}
		repaint();
	}
	
	private void setMenuButton() {
		int x = 30, y = 220;
		
		for(int i = 0; i < this.SIZE; i++) {
			this.menuBtn[i] = new JButton();
			this.menuBtn[i].setBounds(x,y,120,30);
			this.menuBtn[i].setText("PICK");
			this.menuBtn[i].addActionListener(this);
			add(this.menuBtn[i]);		
			x += 120;
			
			if(i % 5 == 4) {
				y += 150;
				x = 30;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int check = -1;
		
		for(int i = 0; i < 4; i++) {
			if(e.getSource() == this.sizeBtn[i]) {
				for(int j = 0; j < 4; j++) {
					if(this.menuSize[j]) {
						this.menuSize[j] = false;
					}
				}
				
				this.menuSize[i] = true;
				this.idx = i;	
								
				this.resultTable.setValueAt(this.priceList[idx] + "", 0, 0);
				JOptionPane.showMessageDialog(null, String.format("���̽�ũ�� %d���� ���� �����ϼ̽��ϴ�.", productCnt[idx]));
			}
			else check = 1;
		}
		
		if(check == 1) {
			boolean chk = false;
			for(int i = 0; i < 4; i++) {
				if(this.menuSize[i]) {
					chk = true;
				}
			}
			
			if(chk && this.totalCnt < productCnt[this.idx]) {
				for(int i = 0; i < SIZE; i++) {
					if(e.getSource() == this.menuBtn[i]) {
						Vector<String> data = new Vector<>();
						data.add(productArr[i]);
						data.add("1");
						this.products.add(data);
					
						if(i < 12) {
							this.totalCnt++; 							
						}
						else {
							this.cakeIdx = i - 12;
							this.priceList[idx] += this.cakePriceList[this.cakeIdx];
							this.cakeCnt++;
						}
						
						this.resultTable.setValueAt(this.priceList[idx] + "", 0, 0);
						this.resultTable.setValueAt((this.totalCnt + this.cakeCnt) +"", 0, 1);
						this.resultTable.revalidate();
						this.resultTable.repaint();
					}
				}			
			}		
			
			else if(e.getSource() == this.payBtn) {
				if(this.idx != -1) {
					payment();
				}
				
				else JOptionPane.showMessageDialog(null, "�޴��� �������ּ���.");
			}
			
			else if(!chk && this.idx == -1){
				JOptionPane.showMessageDialog(null, "���̽�ũ�� ����� ���� �������ּ���.");
			}
			
			else if(this.idx != -1 && this.totalCnt == productCnt[this.idx]){
				JOptionPane.showMessageDialog(null, String.format("���̽�ũ�� %d���� ���� ��� �����ϼ̽��ϴ�.", productCnt[idx]));				
			}
		}		
	}

	private void payment() {
		int price = this.priceList[this.idx];	
		int answer = JOptionPane.showConfirmDialog(null, String.format("�����Ͻ� �ݾ��� %d�� �Դϴ�.",price));									
	
		if(answer == JOptionPane.YES_OPTION) {
			new BRAlertFrame();
		}
	}
}