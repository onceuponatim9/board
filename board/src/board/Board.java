package board;

import java.util.ArrayList;

public class Board {
	private ArrayList<Content> contents;
	private String id;
	
	public Board(String id) {
		contents = new ArrayList<Content>();
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void addContext(Content content) {
		contents.add(content);
	}
	
	public void deleteContext(int index) {
		contents.remove(index);
	}
	
	public void updateContext(int index, int line) {
		Content context = contents.get(index);
	}
	
	public Content get(int index) {
		return contents.get(index);
	}
	
	public int getContentCount() {
		return contents.size();
	}

}
