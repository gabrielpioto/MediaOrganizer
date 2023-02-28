package br.com.mediazer.pathbuilder;

import java.util.Iterator;



public abstract class Statement<T> implements Iterator<Statement<T>> {

	private T bean;
	
	public String setBean(T bean){
		this.bean = bean;
		String result = getResult();
		return (hasNext() ? next().setBean(bean) : "") + result;
	}
	
	protected T getBean(){
		return bean;
	}
	
	public abstract String getResult();
	
	public abstract void addNext(Statement<T> s);
}
