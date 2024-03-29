package board;

import java.util.ArrayList;

public class Board {
	private ArrayList<Context> contexts;
	private String id;
	
	public Board(String id) {
		contexts = new ArrayList<Context>();
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void addContext(Context context) {
		contexts.add(context);
	}
	
	public void deleteContext(int index) {
		contexts.remove(index);
	}
	
	public void updateContext(int index, int line) {
		Context context = contexts.get(index);
	}
	
	public Context get(int index) {
		return contexts.get(index);
	}
	
	public int getContextCount() {
		return contexts.size();
	}

}
