package models;

import java.util.HashMap;
import java.util.Map;

public class Student extends User{
	private long stuCode;
	private int subCnt;
	private Map<String, Integer> sub_score_list = null;
	
	public Student(String id, String pw, String name) {
		super(1, id, pw, name);
	}
	
	@Override
	public void setInfo(long stuCode) {
		this.stuCode = stuCode;
		this.sub_score_list = new HashMap<String, Integer>();
	}

	@Override
	public void printInfo() {
		String info = "";
		info += "[학생]학번: " + this.stuCode + " ID: " + getId() + " PW: " + getPw() + " 이름: " + getName();

		System.out.println(info);
	}
}
