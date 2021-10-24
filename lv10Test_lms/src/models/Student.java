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
		info += "[학생]학번: " + this.stuCode + " ID: " + getId() + " PW: " + getPw() + " 이름: " + getName() + "\n";
		info += "* 수강중인 과목 *";
	
		if(getSubCnt() > 0) {
			for(Subject sub : getSubList()) {
				info += "\n과목: " + sub.getTitle() + " 점수: " + getScore(sub.getTitle()) + "점\n";
			}
		}
		info += "\n";
		System.out.println(info);
	}
	
	@Override
	public String toString() {
		String info = "";
		info += "[학생]학번: " + this.stuCode + " 이름: " + getName();
				
		return info;
	}
}
