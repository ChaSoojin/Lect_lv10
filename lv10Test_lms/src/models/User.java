package models;

public abstract class User {
	public static long log = -1;
	private int key;  //학생[1] or 강사[2] 구분키
	private long code;
	private String id;
	private String pw;
	private String name;
	
	public User(int key, String id, String pw, String name) {
		this.key = key;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public long getKey() {
		return this.key;
	}
	
	public long getCode() {
		return this.code;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract void setInfo(long code);
	public abstract void printInfo();
}
