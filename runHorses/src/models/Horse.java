package models;

import javax.swing.ImageIcon;

public class Horse {
	//state ó�� ���
	private final int RUN = 0;
	private final int GOAL = 1;
	
	private int num; 
	private int x,y,w,h;
	private int state; //������
	private String fileName; //�� �̹��� ������ �ִ� ����
	private ImageIcon image;
	private int rank;
	
	public Horse(int num, String fileName, ImageIcon image) {
		this.num = num;
		this.fileName = fileName;
		this.image = image;
	}
}
