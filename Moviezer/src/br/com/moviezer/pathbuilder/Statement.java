package br.com.moviezer.pathbuilder;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import br.com.moviezer.util.StringUtils;
import br.com.moviezer.util.StringUtils.NullResultException;



public abstract class Statement implements Iterator<Statement> {

	private Object bean; 
	private String command;
	
	public Statement(String command){
		this.command = command;
	}
	
	public String setBean(Object bean){
		this.bean = bean;
		String result = null;
		try{
			result = getResult();
		}catch(NoSuchMethodException | NullResultException ex){
			ex.printStackTrace();
		}
		return (hasNext() ? next().setBean(bean) : "") + result;
	}
	
	protected Object getBean(){
		return bean;
	}
	
	protected String getCommand(){
		return command;
	}
	
	public String getResult() throws NoSuchMethodException, NullResultException{
		String result = null;
		try {
			result = StringUtils.invokeFromCode(getCommand(), getBean());
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
		return result + File.separator;
	}
	
	public abstract void addNext(Statement s);
}
