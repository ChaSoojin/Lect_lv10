package models;

import lms_interface.evaluateable;

public class Instructor extends User implements evaluateable{
	private long insCode;
	
	public Instructor(String id, String pw, String name) {
		super(2, id, pw, name);
	}
	
	@Override
	public void setInfo(long insCode) {
		this.insCode = insCode;
	}

	@Override
	public void printInfo() {
		String info = "";
		info += "[강사]코드: " + this.insCode + " ID: " + getId() + " PW: " + getPw() + " 이름: " + getName();
		
		System.out.println(info);
	}
}
