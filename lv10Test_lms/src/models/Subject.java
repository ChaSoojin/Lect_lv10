package models;

public class Subject {
	private String title;
	
	public Subject(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	@Override
	public String toString() {
		String info = "";
		info += "�����: " + this.title;
		
		return info;
	}
}
