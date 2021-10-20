package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import models.Instructor;
import models.Student;
import models.User;

public class UserController {
	private static UserController instance = new UserController();
	private Map<Long, Object> userList  = null; // {유저코드 : 유저객체 }
	
	private UserController() {
		this.userList = new HashMap<Long, Object>();
	}
	
	public static UserController getInstance() {
		return instance;
	}
	
	public void join() {
		if(User.log == -1) {
			int key = 0;
			System.out.println("1.학생가입   2.강사가입");
			System.out.print("선택> ");
			String input = SystemController.getInstance().getScanClass().next();
			
			try {
				int choice = Integer.parseInt(input);
			
				if(choice == 1) key = 1;
				else if(choice == 2) key = 2;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(key == 1 || key == 2) {
				System.out.print("[회원가입]ID: ");
				String id = SystemController.getInstance().getScanClass().next();
				System.out.print("[회원가입]PW: ");
				String pw = SystemController.getInstance().getScanClass().next();
				
				int chk = check(id);
				
				if(chk == 1) {
					System.out.print("[회원가입]NAME: ");
					String name = SystemController.getInstance().getScanClass().next();
					
					//학생가입
					if(key == 1) {
						long code = ranCode();
						this.userList.put(code, new Student(id, pw, name));
						
						User student = (User)this.userList.get(code);
						student.setInfo(code);
					}
					
					//강사가입
					else if(key == 2) {
						long code = ranCode();
						this.userList.put(code, new Instructor(id, pw, name));
						
						User instructor = (User)this.userList.get(code);
						instructor.setInfo(code);
					}
					
					System.out.println("[성공]회원가입이 완료되었습니다.");
				}
				else System.out.println("[실패]ID 중복오류");			
			}
			
			else System.out.println("[실패]회원가입오류");
		}
	}
	
	//아이디 중복 체크
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
			System.out.print("[로그인]ID: ");
			String id = SystemController.getInstance().getScanClass().next();
			System.out.print("[로그인]PW: ");
			String pw = SystemController.getInstance().getScanClass().next();
			
			long chk = check(id, pw);
			
			if(chk == -1) System.out.println("[로그인실패]회원정보를 다시 확인해주세요.");
			else {
				User.log = chk;
				System.out.printf("[로그인성공]%s님 로그인되었습니다.\n", ((User) userList.get(User.log)).getName());
				
				if(((User) userList.get(User.log)).getKey() == 1) loginSuccess = 1;
				else if(((User) userList.get(User.log)).getKey() == 2) loginSuccess = 2;
			}			
		}
		return loginSuccess;
	}
	
	//로그인 회원정보체크
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
			System.out.println("로그아웃 되었습니다.");
		}
		else System.out.println("로그인 후 이용 바랍니다.");
	}
	
	public void deleteUser() {
		long userCode = -1;
		
		if(User.log != -1) {
			System.out.print("[회원탈퇴]PW: ");
			String pw = SystemController.getInstance().getScanClass().next();
			
			if(((User) this.userList.get(User.log)).getPw().equals(pw)) {
				userCode = ((User) this.userList.get(User.log)).getCode(); 
				this.userList.remove(User.log);
				User.log = -1;
				System.out.println("회원탈퇴완료");
			}
			else System.out.println("[실패]회원정보를 다시 확인해주세요.");				
		}
		else System.out.println("로그인 후 이용 바랍니다.");
		
	}

	public void alignStudents() {
		
	}

	public void printStudents() {
		
	}
	
	public void printAllUsers() {
		System.out.println("------ 유저 리스트 ------");
		for(Object key : this.userList.keySet()) {
			((User)this.userList.get(key)).printInfo();
		}
		System.out.println();
	}
}
