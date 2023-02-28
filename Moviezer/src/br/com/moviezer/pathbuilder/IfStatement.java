package br.com.moviezer.pathbuilder;

import br.com.moviezer.util.StringUtils.NullResultException;


public class IfStatement extends Statement {
	
	private Statement trueNext;
	private Statement falseNext;
	private boolean bNext;
	
	public IfStatement(String command) {
		super(command);
		bNext = true;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Statement next() {
		Statement next = trueNext;
		try{
			super.getResult();
		}catch(NullResultException | NoSuchMethodException ex){
			next = falseNext;
		}
		return next;
	}
	
	@Override
	public String getResult() throws NoSuchMethodException, NullResultException {
		return "";
	}

	@Override
	public void addNext(Statement s) {
		if(bNext) trueNext = s;
		else falseNext = s;
	}
	
	public void setNext(boolean bNext){
		this.bNext = bNext;
	}
	
}
