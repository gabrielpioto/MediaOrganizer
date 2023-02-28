package br.com.mediazer.pathbuilder;

import java.util.function.Predicate;

public class IfStatement<T> extends Statement<T> {

	private Statement<T> trueNext;
	private Statement<T> falseNext;
	private Predicate<T> predicate;
	private boolean bNext;

	public IfStatement(Predicate<T> predicate) {
		this.predicate = predicate;
		bNext = true;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Statement<T> next() {		
		return predicate.test(getBean()) ? trueNext : falseNext;
	}

	@Override
	public String getResult() {
		return "";
	}

	@Override
	public void addNext(Statement<T> s) {
		if (bNext)
			trueNext = s;
		else
			falseNext = s;
	}

	public void setNext(boolean bNext) {
		this.bNext = bNext;
	}

}
