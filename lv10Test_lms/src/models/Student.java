package models;

import java.util.HashMap;
import java.util.Map;

public class Student extends User{
	private long stuCode;
	private Map<String, Integer> scoreList = new HashMap<String, Integer>();
	
	public Student(long code, String id, String pw, String name) {
		super(1, code, id, pw, name);
	}

	@Override
	public void setInfo(long stuCode) {
		this.stuCode = stuCode;
	}
	
	public Integer getScore(String subTitle) {
		return this.scoreList.get(subTitle);
	}

	public Map<String, Integer> getScoreList(){
		return this.scoreList;
	}
	
	public void addScore(String title, Integer score) {
		this.scoreList.put(title, score);
	}
	
	public void addScoreList(String title, int score) {
		this.scoreList.put(title, score);
	}
	
	@Override
	public void printInfo() {
		String info = "";
		info += "[�л�]�й�: " + this.stuCode + " ID: " + getId() + " PW: " + getPw() + " �̸�: " + getName() + "\n";
		info += "* �������� ���� *";
	
		if(getSubCnt() > 0) {
			for(Subject sub : getSubList()) {
				info += "\n����: " + sub.getTitle() + " ����: " + getScore(sub.getTitle()) + "��\n";
			}
		}
		info += "\n";
		System.out.println(info);
	}
	
	@Override
	public String toString() {
		String info = "";
		info += "[�л�]�й�: " + this.stuCode + " �̸�: " + getName();
				
		return info;
	}
}
