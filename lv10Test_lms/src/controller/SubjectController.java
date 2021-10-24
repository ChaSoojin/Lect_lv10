package controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import models.Instructor;
import models.Student;
import models.Subject;
import models.User;

public class SubjectController {
	private static SubjectController instance = new SubjectController();
	private ArrayList<Subject> subjects = null;
	
	private SubjectController() {
		this.subjects = new ArrayList<Subject>();
	}
	
	public static SubjectController getInstance() {
		return instance;
	}

	public ArrayList<Subject> getSubjects(){
		return this.subjects;
	}
	
	public void addSubject() {
		System.out.print("[과목추가]과목명: ");
		String title = SystemController.getInstance().getScanClass().next();

		int check = 1;
		for(int i = 0; i < this.subjects.size(); i++) {			
			if(title.equals(this.subjects.get(i).getTitle())) {
				check = -1;
			}
		}
		
		if(check == 1) {
			//과목추가 동시에 문제답안 랜덤생성
			User user = UserController.getInstance().getUserObject();
			int[] answerList = ((Instructor) user).makeTest(); //강사가 문제생성 
			
			Subject newSub = new Subject(User.log, title, answerList);
			
			this.subjects.add(newSub);			
			user.getSubList().add(newSub);
			user.setSubCnt(user.getSubCnt() + 1);
			System.out.println("[과목추가성공]과목을 추가했습니다.");
		}
		
		else if(check == -1) {
			System.out.println("[과목추가실패]이미 추가된 과목입니다. 다른 과목을 추가해주세요!");
		}
	}

	public void addScore() {
		System.out.println("< 과목별 학생 성적 리스트 >");
		User user = UserController.getInstance().getUserObject();
		Map<Long, Object> uList = UserController.getInstance().getUserList();
		
		for(Subject compareSub : user.getSubList()) {
			System.out.println(compareSub);
			for(Object key : uList.keySet()) {
				if((((User) uList.get(key)).getKey() == 1)) {
					for(String sub : ((Student) uList.get(key)).getScoreList().keySet()) {
						
						if(compareSub.getTitle().equals(sub)) {
							System.out.println(((Student) uList.get(key)));
							System.out.println("점수: " + ((Student) uList.get(key)).getScore(sub) + "점");
						}
					}
				}				
			}
		}
		System.out.println("[성공]학생성적을 불러왔습니다. 자동으로 등록합니다.");
	}

	//수강신청
	public void applySubject() {
		if(this.subjects.size() == 0) {
			System.out.println("과목이 존재하지 않습니다. 관리자에 문의하세요!");
			return;
		}
		
		int numbering = 1;
		
		System.out.println("------ 수강신청 페이지 ------");
		System.out.println("<과목 리스트>");
		for(Subject sub : this.subjects) {
			System.out.print(numbering++ + ". ");
			System.out.println(sub.getTitle());
		}
		System.out.print("과목선택: ");
		String select = SystemController.getInstance().getScanClass().next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < this.subjects.size()) {				
				Student stu = (Student) UserController.getInstance().getUserObject();
				
				boolean chk = true;
				for(Subject s : stu.getSubList()) {
					if(s.getTitle().equals(this.subjects.get(sel).getTitle())) chk = false;
				}
				
				if(chk) {
					stu.getSubList().add(this.subjects.get(sel));
					stu.setSubCnt(stu.getSubCnt() + 1);
					System.out.println("[성공] 수강신청을 완료하였습니다.");					
				}
				else System.out.println("[실패] 이미 수강신청한 과목입니다.");
			}
		} catch (Exception e) {}		
	}
	
	//시험보기
	public void test() {
		Student stu = (Student) UserController.getInstance().getUserObject();
		if(stu.getSubCnt() == 0) {
			System.out.println("시험 볼 과목이 존재하지 않습니다. 강사님께 문의하세요!");
			return;
		}
		
		int numbering = 1;
		
		System.out.println("<수강과목 리스트>");
		for(Subject sub : stu.getSubList()) {
			System.out.print(numbering++ + ". ");
			System.out.println(sub.getTitle());
		}
		System.out.print("시험볼과목선택: ");
		String select = SystemController.getInstance().getScanClass().next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < stu.getSubCnt()) {
				int[] tmpAnswer = new int[5];
				
				System.out.println("-------- TEST --------");
				System.out.println("[답안입력란]");
				for(int i = 0; i < 5; i++) {
					System.out.print("문제 " + (i+1) + ". ");
					String tmpInput = SystemController.getInstance().getScanClass().next();
					
					try {
						int input = Integer.parseInt(tmpInput);
						tmpAnswer[i] = input;
						
					} catch (Exception e) {
						i--;
						System.out.println("[답안입력실패] 다시 입력하세요.");
					}	
				}
				
				System.out.print("최종작성답안: ");
				for(int i : tmpAnswer) {
					System.out.print(i + " ");
				}
				System.out.println("\n----------------------");
				System.out.println("답안을 제출합니다.");
				grading(stu.getSubList().get(sel).getTitle(), tmpAnswer);
			}
			
		} catch (Exception e) {}	
	}

	//채점
	private void grading(String subTitle, int[] stuAnswer) {
		Subject findSub = null;
		
		for(Subject sub : this.subjects) {
			if(sub.getTitle().equals(subTitle)) findSub = sub;
		}
		
		User user = findInstructor(findSub);
		int score = ((Instructor) user).gradingTest(findSub, stuAnswer);
		
		Student stu = (Student) UserController.getInstance().getUserObject();
		stu.addScore(findSub.getTitle(), score);
	}
	
	//시험본 과목에 해당하는 강사 찾기
	private User findInstructor(Subject findSub) {
		User user = null;
	
		for(Long code : UserController.getInstance().getUserList().keySet()) {
			if(findSub.getCode() == code) {
				user = (User) UserController.getInstance().getUserList().get(code);
			}
		}
		
		return user;
	}

	public void printData() {
		System.out.println("---------------------------");
//		System.out.println("<현재 추가된 총 과목 리스트>");
//		for(Subject sub : this.subjects) {
//			System.out.println(sub);
//		}
		
		User user = UserController.getInstance().getUserObject();
		System.out.printf("<%s 강사 과목 & 답안리스트>\n", user.getName());
		for(Subject sub : user.getSubList()) {
				System.out.println(sub);
		}
		System.out.println("---------------------------");
	}

	public void testResult() {
		System.out.println("----- 나의시험결과 -----");
		User user = UserController.getInstance().getUserObject();
		user.printInfo();
	}

	public void subjectSetting(long code, String title, int[] answers) {
		Subject newSub = new Subject(code, title, answers);
		this.subjects.add(newSub);		
		
		Map<Long, Object> userList = UserController.getInstance().getUserList();
		
		for(Long co : userList.keySet()) {
			if(((User) userList.get(co)).getCode() == code) {
				User user = (User) userList.get(code);
				user.getSubList().add(newSub);
				user.setSubCnt(user.getSubCnt() + 1);
			}
		}
	}

	public void scoreSetting(Long code, String title, int score) {
		Map<Long, Object> userList = UserController.getInstance().getUserList();
		
		for(Long co : userList.keySet()) {
			long compareCode = (long)co;
			if(compareCode == code) {
				User user = (User) userList.get(code);
				user.getSubList().add(new Subject(code, title));
				user.setSubCnt(user.getSubCnt() + 1);
				
				((Student) userList.get(code)).addScoreList(title, score);
			}
		}
	}
}
