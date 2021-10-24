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
		System.out.print("[�����߰�]�����: ");
		String title = SystemController.getInstance().getScanClass().next();

		int check = 1;
		for(int i = 0; i < this.subjects.size(); i++) {			
			if(title.equals(this.subjects.get(i).getTitle())) {
				check = -1;
			}
		}
		
		if(check == 1) {
			//�����߰� ���ÿ� ������� ��������
			User user = UserController.getInstance().getUserObject();
			int[] answerList = ((Instructor) user).makeTest(); //���簡 �������� 
			
			Subject newSub = new Subject(User.log, title, answerList);
			
			this.subjects.add(newSub);			
			user.getSubList().add(newSub);
			user.setSubCnt(user.getSubCnt() + 1);
			System.out.println("[�����߰�����]������ �߰��߽��ϴ�.");
		}
		
		else if(check == -1) {
			System.out.println("[�����߰�����]�̹� �߰��� �����Դϴ�. �ٸ� ������ �߰����ּ���!");
		}
	}

	public void addScore() {
		System.out.println("< ���� �л� ���� ����Ʈ >");
		User user = UserController.getInstance().getUserObject();
		Map<Long, Object> uList = UserController.getInstance().getUserList();
		
		for(Subject compareSub : user.getSubList()) {
			System.out.println(compareSub);
			for(Object key : uList.keySet()) {
				if((((User) uList.get(key)).getKey() == 1)) {
					for(String sub : ((Student) uList.get(key)).getScoreList().keySet()) {
						
						if(compareSub.getTitle().equals(sub)) {
							System.out.println(((Student) uList.get(key)));
							System.out.println("����: " + ((Student) uList.get(key)).getScore(sub) + "��");
						}
					}
				}				
			}
		}
		System.out.println("[����]�л������� �ҷ��Խ��ϴ�. �ڵ����� ����մϴ�.");
	}

	//������û
	public void applySubject() {
		if(this.subjects.size() == 0) {
			System.out.println("������ �������� �ʽ��ϴ�. �����ڿ� �����ϼ���!");
			return;
		}
		
		int numbering = 1;
		
		System.out.println("------ ������û ������ ------");
		System.out.println("<���� ����Ʈ>");
		for(Subject sub : this.subjects) {
			System.out.print(numbering++ + ". ");
			System.out.println(sub.getTitle());
		}
		System.out.print("������: ");
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
					System.out.println("[����] ������û�� �Ϸ��Ͽ����ϴ�.");					
				}
				else System.out.println("[����] �̹� ������û�� �����Դϴ�.");
			}
		} catch (Exception e) {}		
	}
	
	//���躸��
	public void test() {
		Student stu = (Student) UserController.getInstance().getUserObject();
		if(stu.getSubCnt() == 0) {
			System.out.println("���� �� ������ �������� �ʽ��ϴ�. ����Բ� �����ϼ���!");
			return;
		}
		
		int numbering = 1;
		
		System.out.println("<�������� ����Ʈ>");
		for(Subject sub : stu.getSubList()) {
			System.out.print(numbering++ + ". ");
			System.out.println(sub.getTitle());
		}
		System.out.print("���躼������: ");
		String select = SystemController.getInstance().getScanClass().next();
		
		try {
			int sel = Integer.parseInt(select) - 1;
			
			if(sel >= 0 && sel < stu.getSubCnt()) {
				int[] tmpAnswer = new int[5];
				
				System.out.println("-------- TEST --------");
				System.out.println("[����Է¶�]");
				for(int i = 0; i < 5; i++) {
					System.out.print("���� " + (i+1) + ". ");
					String tmpInput = SystemController.getInstance().getScanClass().next();
					
					try {
						int input = Integer.parseInt(tmpInput);
						tmpAnswer[i] = input;
						
					} catch (Exception e) {
						i--;
						System.out.println("[����Է½���] �ٽ� �Է��ϼ���.");
					}	
				}
				
				System.out.print("�����ۼ����: ");
				for(int i : tmpAnswer) {
					System.out.print(i + " ");
				}
				System.out.println("\n----------------------");
				System.out.println("����� �����մϴ�.");
				grading(stu.getSubList().get(sel).getTitle(), tmpAnswer);
			}
			
		} catch (Exception e) {}	
	}

	//ä��
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
	
	//���躻 ���� �ش��ϴ� ���� ã��
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
//		System.out.println("<���� �߰��� �� ���� ����Ʈ>");
//		for(Subject sub : this.subjects) {
//			System.out.println(sub);
//		}
		
		User user = UserController.getInstance().getUserObject();
		System.out.printf("<%s ���� ���� & ��ȸ���Ʈ>\n", user.getName());
		for(Subject sub : user.getSubList()) {
				System.out.println(sub);
		}
		System.out.println("---------------------------");
	}

	public void testResult() {
		System.out.println("----- ���ǽ����� -----");
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
