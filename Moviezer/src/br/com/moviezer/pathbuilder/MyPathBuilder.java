package br.com.moviezer.pathbuilder;

import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.moviezer.util.SimpleStack;

public class MyPathBuilder {

	private Statement head;
	private Statement last;
	private SimpleStack<IfStatement> endIf;
	private SimpleStack<Statement> endElse;
	
	public MyPathBuilder() {
		endIf = new SimpleStack<IfStatement>();
		endElse = new SimpleStack<Statement>();
		last = head = new AddStatement("");
	}
	
	public MyPathBuilder add(String code){
		AddStatement addstt = new AddStatement(code);
		
		last.addNext(addstt);
		last = addstt;
		
		return this;
	}
	
	public MyPathBuilder If(String code){
		IfStatement ifstt = new IfStatement(code);
		endIf.push(ifstt);
		last.addNext(ifstt);
		last = ifstt;
		return this;
	}
	
	public MyPathBuilder Else(){
		
		IfStatement ifstt = endIf.pop();
		ifstt.setNext(false);
		
		endElse.push(last); //talvez precise do clone
		last = ifstt;
		
		return this;
	}
	
	public MyPathBuilder endIf(){
		IfStatement ifstt = endIf.pop();
		ifstt.setNext(false);
		AddStatement temp = new AddStatement("");
		ifstt.addNext(temp);
		last.addNext(temp);
		last = temp;		
		return this;
	}
	
	public MyPathBuilder endElse(){
		Statement stt = endElse.pop();
		AddStatement temp = new AddStatement("");
		stt.addNext(temp);
		last.addNext(temp);
		last = temp;
		
		return this;
	}
	
	public String build(Object bean){
		return head.setBean(bean);
	}
	
	
	
}
