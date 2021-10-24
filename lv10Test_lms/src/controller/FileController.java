package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import models.Student;
import models.Subject;
import models.User;

public class FileController {
	private static FileController instance = new FileController();
	private UserController uc = UserController.getInstance();
	private SubjectController sc = SubjectController.getInstance();
	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	private File file = null;

	private String fileName1 = "user.txt";
	private String fileName2 = "subject.txt";

	public static FileController getInstance() {
		return instance;
	}
	
	public void save() {
		try {
			fw = new FileWriter(fileName1);
			fw.write(makeUserData());
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			fw = new FileWriter(fileName2);
			fw.write(makeSubjectData());
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String makeUserData() {
		String data = "";
		Map<Long, Object> uList = UserController.getInstance().getUserList();
		
		for (Long code : uList.keySet()) {			
			data += ((User) uList.get(code)).getKey() + "/";
			data += ((User) uList.get(code)).getCode() + "/";
			data += ((User) uList.get(code)).getId() + "/";
			data += ((User) uList.get(code)).getPw() + "/";
			data += ((User) uList.get(code)).getName() + "/";
			data += ((User) uList.get(code)).getSubCnt() + "/";
			
			if(((User) uList.get(code)).getSubCnt() > 0) {
				if(((User) uList.get(code)).getKey() == 1) {
					
					for(String sub : ((Student) uList.get(code)).getScoreList().keySet()) {
						data += sub + "/";
						data += ((Student) uList.get(code)).getScoreList().get(sub) + "/";
					}
				}
				
				if(((User) uList.get(code)).getKey() == 2) {
					for(Subject sub : ((User) uList.get(code)).getSubList()) {
						data += sub.getTitle() + "/";
					}
				}
				data = data.substring(0, data.length() - 1);
			}
			data += "\n";
		}
		data = data.substring(0, data.length() - 1);

		return data;
	}

	private String makeSubjectData() {
		String data = "";
		for(Subject sub : SubjectController.getInstance().getSubjects()) {
			data += sub.getCode() + "/";
			data += sub.getTitle() + "/";
			
			for(int i = 0; i < sub.getAnswer().length; i++) {
				data += sub.getAnswer()[i];
				
				if(i < sub.getAnswer().length-1) data += "/";
			}
			data += "\n";
		}
		data = data.substring(0, data.length() - 1);
		
		return data;
	}

	public void load() {
		uc.getUserList().clear();
		sc.getSubjects().clear();
		
		String data = loadContent(fileName1);
		if(!data.equals("")) userDataSetting(data);
		
		data = loadContent(fileName2);
		if(!data.equals("")) subDataSetting(data);
	}

	private String loadContent(String fileName) {
		file = new File(fileName);
		String data = "";
		
		if(file.exists()) {
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				String line;
				
				while((line = br.readLine()) != null) {
					data += line + "\n";
				}
				data = data.substring(0, data.length()-1);
				
			} catch (Exception e) {
				// TODO: handle exception
			}					
		}
		return data;
	}

	private void userDataSetting(String data) {
		String[] info = data.split("\n");
		for(int i = 0; i < info.length; i++) {
			String[] info2 = info[i].split("/");
			
			int key = Integer.parseInt(info2[0]);
			long code = Integer.parseInt(info2[1]);
			
			uc.userSetting(key, code, info2[2], info2[3], info2[4]);
			
			int subCnt = Integer.parseInt(info2[5]);

			if(key == 1) {	
				for(int j = 0; j < subCnt; j++) {
					String title = info2[6 + (j * 2)];
					int score = Integer.parseInt(info2[7 + (j * 2)]);
					sc.scoreSetting(code, title, score);
				}
			}
		}
	}
	
	private void subDataSetting(String data) {
		String[] info = data.split("\n");
		
		for(int i = 0; i < info.length; i++) {
			String[] info2 = info[i].split("/");
			
			long code = Integer.parseInt(info2[0]);
			
			int[] answers = new int[5];
			for(int j = 0; j < 5; j++) {
				answers[j] = Integer.parseInt(info2[2 + j]); //2~6
			}
			sc.subjectSetting(code, info2[1], answers);
		}
	}
}
