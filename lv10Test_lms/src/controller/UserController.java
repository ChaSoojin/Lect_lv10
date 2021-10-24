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
	
	public Map<Long, Object> getUserList(){
		return this.userList;
	}
	
	public User getUserObject() {
		return (User) this.userList.get(User.log);
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
						this.userList.put(code, new Student(code, id, pw, name));
						
						User student = (User)this.userList.get(code);
						student.setInfo(code);
					}
					
					//강사가입
					else if(key == 2) {
						long code = ranCode();
						this.userList.put(code, new Instructor(code, id, pw, name));
						
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
		else System.out.println("[실패]이미 로그인중입니다.");
		
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
		Object[] keys = null;

		System.out.println("1.이름순   2.학번순");
		String select = SystemController.getInstance().getScanClass().next();
		
		try {
			int sel = Integer.parseInt(select);
			
			if(sel == 1) keys = alignbyName();
			else if(sel == 2) keys = alignbyCode();
			
		} catch (Exception e) {}
	
		
		for(int i = 0; i < keys.length; i++) {
			if(((User)this.userList.get(keys[i])).getKey() == 1) {
				((User)this.userList.get(keys[i])).printInfo();
			}
		}
	}

	//학번순 정렬
	private Object[] alignbyCode() {
		Object[] keys = this.userList.keySet().toArray();
		for(int i = 0; i < keys.length; i++) {
			for(int j = i+1; j < keys.length; j++) {
				if((long)keys[i] > (long)keys[j]) {
					Object tmp = keys[i];
					keys[i] = keys[j];
					keys[j] = tmp;
				}
			}
		}		
		
		return keys;
	}

	//이름순 정렬
	private Object[] alignbyName() {
		Object[] keys = this.userList.keySet().toArray();
		for(int i = 0; i < keys.length; i++) {
			for(int j = i+1; j < keys.length; j++) {
				String name = ((User) this.userList.get(keys[i])).getName();
				
				if(((User) this.userList.get(keys[j])).getName().compareTo(name) < 0) {
					Object tmp = (long) keys[i];
					keys[i] = keys[j];
					keys[j] = tmp;
				}
			}
		}		
		
		return keys;
	}

	public void printStudents() {
		System.out.println("------ 학생 리스트 ------");
		for(Object key : this.userList.keySet()) {
			if(((User)this.userList.get(key)).getKey() == 1) {
				((User)this.userList.get(key)).printInfo();
			}
		}
		System.out.println();
	}
	
	public void printAllUsers() {
		System.out.println("------ 유저 리스트 ------");
		for(Object key : this.userList.keySet()) {
			((User)this.userList.get(key)).printInfo();
		}
		System.out.println();
	}
	
	public void userSetting(int key, long code, String id, String pw, String name) {		
		//학생가입
		if(key == 1) {
			this.userList.put(code, new Student(code, id, pw, name));
			User student = (User)this.userList.get(code);
			student.setInfo(code);
		}
		
		//강사가입
		else if(key == 2) {
			this.userList.put(code, new Instructor(code, id, pw, name));	
			User instructor = (User)this.userList.get(code);
			instructor.setInfo(code);
		}
	}
}
