package models;

import javax.swing.ImageIcon;

public class Horse {
	//state 처리 상수
	private final int RUN = 0;
	private final int GOAL = 1;
	
	private int num; 
	private int x,y,w,h;
	private int state; //말상태
	private String fileName; //말 이미지 가지고 있는 파일
	private ImageIcon image;
	private int rank;
	
	public Horse(int num, String fileName, ImageIcon image) {
		this.num = num;
		this.fileName = fileName;
		this.image = image;
	}
}
