package br.com.moviezer.pathbuilder;

public class AddStatement extends Statement {

	public AddStatement(String command) {
		super(command);
	}

	private Statement next;
	
	public void addNext(Statement next){
		this.next = next;
	}

	@Override
	public boolean hasNext() {
		
		return next!=null;
	}

	@Override
	public Statement next() {
		return next;
	}

}
