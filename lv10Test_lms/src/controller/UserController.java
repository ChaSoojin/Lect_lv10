package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import models.Instructor;
import models.Student;
import models.User;

public class UserController {
	private static UserController instance = new UserController();
	private Map<Long, Object> userList  = null; // {�����ڵ� : ������ü }
	
	private UserController() {
		this.userList = new HashMap<Long, Object>();
	}
	
	public static UserController getInstance() {
		return instance;
	}
	
	public void join() {
		if(User.log == -1) {
			int key = 0;
			System.out.println("1.�л�����   2.���簡��");
			System.out.print("����> ");
			String input = SystemController.getInstance().getScanClass().next();
			
			try {
				int choice = Integer.parseInt(input);
			
				if(choice == 1) key = 1;
				else if(choice == 2) key = 2;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(key == 1 || key == 2) {
				System.out.print("[ȸ������]ID: ");
				String id = SystemController.getInstance().getScanClass().next();
				System.out.print("[ȸ������]PW: ");
				String pw = SystemController.getInstance().getScanClass().next();
				
				int chk = check(id);
				
				if(chk == 1) {
					System.out.print("[ȸ������]NAME: ");
					String name = SystemController.getInstance().getScanClass().next();
					
					//�л�����
					if(key == 1) {
						long code = ranCode();
						this.userList.put(code, new Student(id, pw, name));
						
						User student = (User)this.userList.get(code);
						student.setInfo(code);
					}
					
					//���簡��
					else if(key == 2) {
						long code = ranCode();
						this.userList.put(code, new Instructor(id, pw, name));
						
						User instructor = (User)this.userList.get(code);
						instructor.setInfo(code);
					}
					
					System.out.println("[����]ȸ�������� �Ϸ�Ǿ����ϴ�.");
				}
				else System.out.println("[����]ID �ߺ�����");			
			}
			
			else System.out.println("[����]ȸ�����Կ���");
		}
	}
	
	//���̵� �ߺ� üũ
	private int check(String id) {
		int chk = 1;
				
		for(Object key : this.userList.keySet()) {
			if(((User) userList.get(key)).getId().equals(id)) chk = -1;
		}
		
		return chk;
	}
	
	private long ranCode() {
		Random r = new Random();
		
		while(true) {
			int rCode = r.nextInt(9000) + 1000;
			
			boolean chk = true;
			for(Object key : this.userList.keySet()) {
				if(((User) userList.get(key)).getCode() == rCode) chk = false;
			}

			if(chk) return rCode;
		}
	}
	
	public int login() {
		int loginSuccess = -1;
		
		if(User.log == -1) {
			System.out.print("[�α���]ID: ");
			String id = SystemController.getInstance().getScanClass().next();
			System.out.print("[�α���]PW: ");
			String pw = SystemController.getInstance().getScanClass().next();
			
			long chk = check(id, pw);
			
			if(chk == -1) System.out.println("[�α��ν���]ȸ�������� �ٽ� Ȯ�����ּ���.");
			else {
				User.log = chk;
				System.out.printf("[�α��μ���]%s�� �α��εǾ����ϴ�.\n", ((User) userList.get(User.log)).getName());
				
				if(((User) userList.get(User.log)).getKey() == 1) loginSuccess = 1;
				else if(((User) userList.get(User.log)).getKey() == 2) loginSuccess = 2;
			}			
		}
		return loginSuccess;
	}
	
	//�α��� ȸ������üũ
	private long check(String id, String pw) {
		long chk = -1;
		
		for(Object key : this.userList.keySet()) {
			if(((User) userList.get(key)).getId().equals(id) && ((User) userList.get(key)).getPw().equals(pw)) chk = (long) key;
		}
		
		return chk;
	}

	public void logout() {
		if(User.log != -1) {
			User.log = -1;
			System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
		}
		else System.out.println("�α��� �� �̿� �ٶ��ϴ�.");
	}
	
	public void deleteUser() {
		long userCode = -1;
		
		if(User.log != -1) {
			System.out.print("[ȸ��Ż��]PW: ");
			String pw = SystemController.getInstance().getScanClass().next();
			
			if(((User) this.userList.get(User.log)).getPw().equals(pw)) {
				userCode = ((User) this.userList.get(User.log)).getCode(); 
				this.userList.remove(User.log);
				User.log = -1;
				System.out.println("ȸ��Ż��Ϸ�");
			}
			else System.out.println("[����]ȸ�������� �ٽ� Ȯ�����ּ���.");				
		}
		else System.out.println("�α��� �� �̿� �ٶ��ϴ�.");
		
	}

	public void alignStudents() {
		
	}

	public void printStudents() {
		
	}
	
	public void printAllUsers() {
		System.out.println("------ ���� ����Ʈ ------");
		for(Object key : this.userList.keySet()) {
			((User)this.userList.get(key)).printInfo();
		}
		System.out.println();
	}
}
