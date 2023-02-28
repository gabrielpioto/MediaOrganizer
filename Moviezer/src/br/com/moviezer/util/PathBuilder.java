package br.com.moviezer.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.moviezer.util.StringUtils.NullResultException;

public class PathBuilder {
	
	private int level = 0;
	private Object bean = null;
	private String path = "";
	private int lookingForEndIf = -1;
	private int lookingForEndElse = -1;
	
	public PathBuilder(){}
	
	public PathBuilder(String filename){
		this(filename, null);
	}
	
	public PathBuilder(String filename, Object bean){
		setFilename(filename);
		setBean(bean);
	}
	
	private PathBuilder add(String code, boolean concat) throws NullResultException, NoSuchMethodException{
		if(lookingForEndIf>=0 || lookingForEndElse>=0) return this;
		String result = null;
		try {
			result = StringUtils.invokeFromCode(code, bean);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
		if(concat) path = File.separator + result + path;
		return this;
	}
	
	public PathBuilder add(String code){
		PathBuilder pb = null; 
		try{
			pb = add(code, true);
		}catch(NullResultException | NoSuchMethodException ex){
			ex.printStackTrace();
		}
		return pb;
	}
	
	public PathBuilder If(String code){
		try{
			add(code, false);
		}catch(NullResultException | NoSuchMethodException ex){
			lookingForEndIf = level;
		}
		level++;
		return this;
	}
	
	public PathBuilder Else(){
		//endIf();
		level--;
		if(lookingForEndIf == level) {
			lookingForEndIf = -1;
		} else {
			if(lookingForEndElse == -1)
				lookingForEndElse = level;
		}
		level++;
		return this;
	}
	
	public PathBuilder endIf(){
		level--;
		if(lookingForEndIf == level){
			lookingForEndIf = -1;
		}
		return this;
	}
	
	public PathBuilder endElse(){
		level--;
		if(lookingForEndElse == level){
			lookingForEndElse = -1;
		}
		return this;
	}
	
	public Path build() {
		if(level!=0) {System.out.println("Syntax error");}//Throw if else dont match
		
		Path result = Paths.get(path);
		path = "";
		return result;
	}
	
	public void setBean(Object bean){
		this.bean = bean;
	}
	
	public void setFilename(String filename){
		path = File.separator + filename;
	}
	
}
