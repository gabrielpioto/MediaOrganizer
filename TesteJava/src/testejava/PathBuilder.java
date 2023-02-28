package testejava;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class PathBuilder<T> {
	
	public PathBuilder() {
		
	}
	
	
	public PathBuilder If(Predicate<T> p){
		
		return this;
	}
	
	public PathBuilder add(Supplier<String> sup){
		
		return this;
	}

}
