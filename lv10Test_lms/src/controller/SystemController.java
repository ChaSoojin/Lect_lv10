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
			System.out.println("1.회원가입  2.회원탈퇴  3.로그인  4.로그아웃  0.종료\n");
			System.out.print("선택> ");
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
			System.out.println("1.시험보기\n2.채점하기\n0.메인으로");
			System.out.print("선택> ");
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
			System.out.println("1.학생정렬\n2.학생출력\n3.과목추가\n4.성적입력\n0.메인으로");
			System.out.print("선택> ");
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
