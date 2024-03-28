package board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Space {
	private Scanner scan = new Scanner(System.in);
	
	Map<User, Board> map = new HashMap<User, Board>();
	
	private String fileName = "board.txt";
	
	private File file = new File(fileName);
	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	
	private int log = -1;
	
	public Space() {
		
	}
	
	public void join() {
		
	}
	
	public void leave() {
		
	}
	
	public void login() {
		
	}
	
	public void logout() {
		
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
	
	private void myPage() {
		
	}
	
	public void run() {
		while(true) {
			
		}
	}
}
