package br.com.mediazer.pathbuilder;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;

public class PathBuilder<T> {

	private Statement<T> head, last;
	private LinkedList<IfStatement<T>> endIf;
	private LinkedList<Statement<T>> endElse;
	private final Function<T,Object> blankFunction = o->"";
	
	public PathBuilder(){
		endIf = new LinkedList<>();
		endElse = new LinkedList<>();
		last = head = new AddStatement<>(blankFunction);
	}
	
	//Object, pois toString
	public PathBuilder<T> add(Function<T, Object> function){
		AddStatement<T> addstt = new AddStatement<>(function);
		
		last.addNext(addstt);
		last = addstt;
		
		return this;
	}
	
	public PathBuilder<T> If(Predicate<T> predicate){
		IfStatement<T> ifstt = new IfStatement<>(predicate);
		endIf.push(ifstt);
		last.addNext(ifstt);
		last = ifstt;
		return this;
	}
	
	public PathBuilder<T> Else(){
		
		IfStatement<T> ifstt = endIf.pop();
		ifstt.setNext(false);
		
		endElse.push(last); //talvez precise do clone
		last = ifstt;
		
		return this;
	}
	
	public PathBuilder<T> endIf(){
		IfStatement<T> ifstt = endIf.pop();
		ifstt.setNext(false);
		join(ifstt);		
		return this;
	}
	
	public PathBuilder<T> endElse(){
		join(endElse.pop());		
		return this;
	}
	
	private void join(Statement<T> stt){
		AddStatement<T> temp = new AddStatement<>(blankFunction);
		stt.addNext(temp);
		last.addNext(temp);
		last = temp;		
	}
	
	public String build(T bean){
		return head.setBean(bean);
	}	
	
}
