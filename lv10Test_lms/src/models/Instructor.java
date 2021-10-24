package models;

import java.util.Random;
import lms_interface.evaluateable;

public class Instructor extends User implements evaluateable{
	private long insCode;
	
	public Instructor(long code, String id, String pw, String name) {
		super(2, code, id, pw, name);
	}
	
	@Override
	public void setInfo(long insCode) {
		this.insCode = insCode;
	}

	@Override
	public void printInfo() {
		String info = "";
		info += "[강사]코드: " + this.insCode + " ID: " + getId() + " PW: " + getPw() + " 이름: " + getName() + "\n";
		info += "* 강의중인 과목 *";
		
		if(getSubCnt() > 0) {
			for(Subject sub : getSubList()) {
				info += "\n과목: " + sub.getTitle();
			}
		}
		info += "\n";
		System.out.println(info);
	}

	@Override
	public int[] makeTest() {
		int[] answers = new int[5];
		Random ran = new Random();
	
		for(int i = 0; i < 5; i++) {
			int answer = ran.nextInt(5) + 1; //1~5번 객관식
			answers[i] = answer;
		}
		
		return answers;
	}

	@Override
	public int gradingTest(Subject findSub, int[] stuAnswer) {
		int cnt = 0, score = 0;
		int[] ans = findSub.getAnswer();
		for(int i = 0; i < ans.length; i++) {
			if(ans[i] == stuAnswer[i]) cnt++;
		}
		
		score = cnt * 20;
		
		return score;
	}
}
