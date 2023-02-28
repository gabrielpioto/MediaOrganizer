package testejava.pathbuilder;

import java.io.File;
import java.util.function.Function;

public class AddStatement<T> extends Statement<T> {

	private Function<T, Object> function;
	private Statement<T> next;

	public AddStatement(Function<T, Object> function) {
		this.function = function;
	}

	public void addNext(Statement<T> next) {
		this.next = next;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public Statement<T> next() {
		return next;
	}

	@Override
	public String getResult() {
		return File.separator + function.apply(getBean());
	}

}
