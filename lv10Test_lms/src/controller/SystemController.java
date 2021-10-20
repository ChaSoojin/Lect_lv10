package controller;

import java.util.Scanner;

import models.User;

public class SystemController {
	private static SystemController instance = new SystemController();
	private Scanner sc = new Scanner(System.in);
	
	private void SystemController() {
		
	}
	
	public static SystemController getInstance() {
		return instance;
	}
	
	public Scanner getScanClass() {
		return sc;
	}
	
	public void run() {
		mainMenu();
	}

	private void mainMenu() {
		boolean run = true;
		while(run) {
			UserController.getInstance().printAllUsers();
			
			System.out.println("=========== LMS SYSTEM Main Page ============");
			System.out.println("1.ȸ������  2.ȸ��Ż��  3.�α���  4.�α׾ƿ�  0.����\n");
			System.out.print("����> ");
			String select = sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				
				if(sel == 1) UserController.getInstance().join();
				else if(sel == 2) UserController.getInstance().deleteUser();
				else if(sel == 3) {
					int chk = UserController.getInstance().login();
					if(chk == 1) studentMenu();
					else if(chk == 2) instructorMenu();
				}
				else if(sel == 4) UserController.getInstance().logout();
				else if(sel == 0) run = false;
					
			} catch (Exception e) {}	
		}
	}
	
	private void studentMenu() {
		boolean run = true;
		while(run) {
			System.out.println("=== Student MENU ===");
			System.out.println("1.���躸��\n2.ä���ϱ�\n0.��������");
			System.out.print("����> ");
			String select = sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				
				if(sel == 1) SubjectController.getInstance().test();
				else if(sel == 2) SubjectController.getInstance().grading();
				else if(sel == 0) {
					User.log = -1;
					run = false;
				}
				
			} catch (Exception e) {}				
		}
	}

	private void instructorMenu() {
		boolean run = true;
		while(run) {
			System.out.println("=== Instructor MENU ===");
			System.out.println("1.�л�����\n2.�л����\n3.�����߰�\n4.�����Է�\n0.��������");
			System.out.print("����> ");
			String select = sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				
				if(sel == 1) UserController.getInstance().alignStudents();
				else if(sel == 2) UserController.getInstance().printStudents();
				else if(sel == 3) SubjectController.getInstance().addSubject();
				else if(sel == 4) SubjectController.getInstance().addScore();
				else if(sel == 0) {
					User.log = -1;
					run = false;
				}
				
			} catch (Exception e) {}						
		}
	}
}
