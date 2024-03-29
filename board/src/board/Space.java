package board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Space {
	private Scanner scan = new Scanner(System.in);
	
	Map<User, Board> map = new HashMap<User, Board>();
	private UserManager um = new UserManager();
	
	private String fileName = "board.txt";
	
	private File file = new File(fileName);
	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	
	private int log = -1;
	
	public Space() {
		
	}
	
	private int inputNumber(String message) {
		int number = -1;
		try {
			System.out.print(message + " : ");
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch(Exception e) {
			System.out.println("숫자를 입력하세요.");
		}
		
		return number;
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		return scan.next();
	}
	
	public void join() {
		String name = inputString("name");
		String id = inputString("id");
		String pw = inputString("pw");
		
		String userId = um.findUserId(id);
		
		if(userId != null) {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}
		
		um.createUser(name, id, pw);
		
		System.out.println("회원가입 완료");
	}
	
	public void leave() {
		User user = um.getUserByLog(log);
		String pw = inputString("pw");
		if(!user.getPassword().equals(pw)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		um.removeUser(user);
		
		log = -1;
		System.out.println("회원탈퇴 완료");
	}
	
	public void login() {
		String id = inputString("id");
		String pw = inputString("pw");
		
		log = um.getUserLogByIdAndPassword(id, pw);
		
		if(log == -1) {
			System.out.println("로그인 정보가 일치하지 않습니다.");
			return;
		}
		
		System.out.println("로그인 성공");
	}
	
	public void logout() {
		log = -1;
		System.out.println("로그아웃 완료");
	}
	
	private void writeContext() {
		
	}
	
	private void saveMyBoard() {
		
	}
	
	private void deleteContext() {
		
	}
	
	private void nextPage() {
		
	}
	
	private void previousPage() {
		
	}
	
	private void myBoard() {
		
	}
	
	private void printMenu() {
		System.out.printf("[1] %s\n", log == -1 ? "회원가입" : "탈퇴");
		System.out.printf("[2] %s\n", log == -1 ? "로그인" : "로그아웃");
		System.out.println("[3] 내 게시판으로 이동");
	}
	
	private void runMenu(int select) {
		if(select == 1 && log == -1)
			join();
		else if(select == 1 && log != -1)
			leave();
		else if(select == 2 && log == -1)
			login();
		else if(select == 2 && log != -1)
			logout();
		else if(select == 3)
			myBoard();
	}
	
	public void run() {
		while(true) {
			System.out.println("회원 " + um.getUserCount() + "명");
			System.out.println("log = " + log);
			printMenu();
			int select = inputNumber("menu");
			runMenu(select);
		}
	}
}
