package models;

public class Subject {
	private long code; //����ü ������ �ִ� �����ڵ�
	private String title;
	private int[] answers = new int[5]; //������
	
	public Subject(long code, String title, int[] answers) {
		this.code = code;
		this.title = title;
		this.answers = answers;
	}

	public Subject(long code, String title) {
		this.code = code;
		this.title = title;
	}

	public long getCode() {
		return this.code;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int[] getAnswer() {
		return this.answers;
	}
	
	@Override
	public String toString() {
		String info = "";
		
		info += "�����: " + this.title + " ���: ";
		info += "[";
		for(int i = 0; i < this.answers.length; i++) {
			info += this.answers[i];
			
			if(i < this.answers.length-1) info += " ";
		}
		info += "]\n";
		
		return info;
	}
}
