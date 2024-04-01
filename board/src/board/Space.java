package board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Space {
	private Scanner scan = new Scanner(System.in);
	
	private Map<User, Board> map;
	private UserManager um = null;
	
	private String fileName = "board.txt";
	
	private File file = new File(fileName);
	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	
	private int log = -1;
	
	public Space() {
		um = UserManager.getInstance();
		map = um.cloneMap();
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
		System.out.print(message);
		return scan.next();
	}
	
	private String inputText(String message) {
		System.out.print(message);
		return scan.nextLine();
	}
	
	public void join() {
		String name = inputString("name : ");
		String id = inputString("id : ");
		String pw = inputString("pw : ");
		
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
		String pw = inputString("pw : ");
		
		if(!user.getPassword().equals(pw)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		um.removeUser(user);
		
		log = -1;
		System.out.println("회원탈퇴 완료");
	}
	
	public void login() {
		String id = inputString("id : ");
		String pw = inputString("pw : ");
		
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
	
	private void createContent() {
		// 게시물 추가
		//String title = inputString("제목 입력 >> ");
		System.out.print("제목 입력 >> ");
		scan.nextLine();
		String title = scan.nextLine();
		
		//String text = inputString("내용 입력 >> ");
		System.out.print("내용 입력 >> ");
		String text = scan.nextLine();
		//System.out.println(scan.nextLine());
		
		Content context = new Content(title, text);
		Board board = um.getBoardByLog(log);
		
		board.addContext(context);
		System.out.println("게시물 업로드 완료");
	}
	
	private void showContentsInfo() {
		Board board = um.getBoardByLog(log);
		for(int i = 0; i < board.getContentCount(); i++) {
			Content context = board.get(i);
			System.out.printf("%d) %s\n", i + 1, context.getTitle());
		}
	}
	
	private void deleteContent() {
		showContentsInfo();
		
		Board board = um.getBoardByLog(log);
		
		if(board.getContentCount() == 0) {
			System.out.println("게시글의 개수가 0개입니다.");
			return;
		}
		
		int index = inputNumber("삭제할 게시글 번호") - 1;
		
		if(index < 0 || index >= board.getContentCount()) {
			System.out.println("유효한 게시글 번호가 아닙니다.");
			return;
		}
		
		board.deleteContext(index);
		System.out.println("선택한 게시글이 삭제되었습니다.");
	}
	
	private void updateContent(String message) {
		// 게시물 수정
		// 내 게시글 목록 (번호, 제목) 보여주기
		// 번호 선택하면 해당 게시글로 보여주기
		// 제목 수정 yes or no
		// 내용 수정 yes or no
		
		Board board = um.getBoardByLog(log);
		
		if(board.getContentCount() == 0) {
			System.out.println("게시글의 개수가 0개입니다.");
			return;
		}
		
		int index = printContent("수정");
		
		int sel = inputNumber("제목 수정 1)yes 2)no : ");
		if(sel < 1 || sel > 2) {
			System.out.println("유효한 메뉴 번호가 아닙니다.");
			return;
		}
		
		if(sel == 1) {
			String title = inputString("제목 입력 >> ");
			board.get(index).setTitle(title);
		}
		
		sel = inputNumber("내용 수정 1)yes 2)no : ");
		if(sel < 1 || sel > 2) {
			System.out.println("유효한 메뉴 번호가 아닙니다.");
			return;
		}
		
		if(sel == 1) {
			String text = inputString("내용 입력 >> ");
			board.get(index).setText(text);
		}
		
		System.out.println("게시글이 성공적으로 수정되었습니다.");
	}
	
	private int printContent(String message) {
		showContentsInfo();
		
		int index = inputNumber(message + "할 게시글 번호") - 1;
		Board board = um.getBoardByLog(log);
		
		if(index < 0 || index >= board.getContentCount()) {
			System.out.println("유효한 게시글 번호가 아닙니다.");
			return -1;
		}
		
		Content content = board.get(index);
		
		System.out.println("제목 >> " + content.getTitle());
		System.out.println("내용 >> " + content.getText());
		
		return index;
	}
	
	private String createDataString() {
		String data = "";
		
		for (User user : map.keySet()) {
			data += user.getName() + "/" + user.getId() + "/" + user.getPassword() + "/";
			Board board = um.getBoardByUser(user);
			
			for(int i = 0; i < board.getContentCount(); i++) {
				data += board.get(i).getTitle() + "/" + board.get(i).getText();
				if(i < board.getContentCount() - 1)
					data += "/";
			}
			data += "\n";
		}
		
		return data;
	}
	
	private void saveMyBoard() {
		// name1/id1/password1/title1/text1/title2/text2
		// name2/id2/password2/title1/text1/title2/text2/title3/text3
		// name3/id3/password3/
		
		String data = createDataString();
		try {
			fw = new FileWriter(file);
			fw.write(data);
			fw.close();
			
			System.out.println("파일저장 성공");
			
		}catch(IOException e) {
			System.err.println("파일저장 실패");
		}
	}
	
	private void loadDataOfBoards(FileReader fr, BufferedReader br) {
		//map = new HashMap<User, Board>();
		
		try {
			while(br.ready()) {
				String[] info = br.readLine().split("/");
				String name = info[0];
				String id = info[1];
				String pw = info[2];
				
				um.createUser(name, id, pw);
				
				User user = new User(name, id, pw);
				Board board = um.getBoardByUser(user);
				
				if(info.length > 3) {
					int size = (info.length - 3) / 2;
					for(int i = 0; i < size; i += 2) {
						String title = info[i];
						String text = info[i + 1];
						Content content = new Content(title, text);
						board.addContext(content);
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("failed");
		}
	}
	
	private void loadMyBoard() {
		if(file.exists()){		
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				loadDataOfBoards(fr, br);
				
				fr.close();
				br.close();
				System.out.println("파일로드 성공");
				
			}catch(IOException e) {
				e.printStackTrace();
				System.err.println("파일로드 실패");
			}
		}else {
			System.err.println("파일이 존재하지 않습니다.");
		}
	}
	
	private void printMyPage() {
		System.out.println("1. 게시글 추가");
		System.out.println("2. 게시글 삭제");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 보기");
	}
	
	private void runMyPage(int select) {
		if(select == 1)
			createContent();
		else if(select == 2)
			deleteContent();
		else if(select == 3)
			updateContent("수정");
		else if(select == 4)
			printContent("조회");
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
		else if(select == 3) {
			if(log == -1) {
				System.out.println("로그인 후 이용 가능한 메뉴입니다.");
				return;
			}
			printMyPage();
			runMyPage(inputNumber(""));
		}
	}
	
	public void run() {
		loadMyBoard();
		while(true) {
			System.out.println("회원 " + um.getUserCount() + "명");
			System.out.println("log = " + log);
			printMenu();
			int select = inputNumber("menu");
			runMenu(select);
			saveMyBoard();
		}
	}
}