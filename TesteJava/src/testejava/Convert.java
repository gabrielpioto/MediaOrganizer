package testejava;

import java.lang.reflect.Field;
import java.util.Map;

public class Convert {
	
	public static<T> T to(Class<T> c , Map<String,String> data) throws InstantiationException, IllegalAccessException{
		T t = c.newInstance();
		Field[] fields = c.getDeclaredFields();
		for(Field f : fields){
			f.setAccessible(true);
			f.set(t,data.get(f.getName()));
		}
		
		return t;
	}
	
	
	
}
