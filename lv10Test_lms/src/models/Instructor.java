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
		info += "[����]�ڵ�: " + this.insCode + " ID: " + getId() + " PW: " + getPw() + " �̸�: " + getName();
		
		System.out.println(info);
	}
}
