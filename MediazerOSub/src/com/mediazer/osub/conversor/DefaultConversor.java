package com.mediazer.osub.conversor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DefaultConversor<T> implements Conversor<T,Map<String,?>>{
	
	@Override
	public T convert(Class<T> klass, Map<String, ?> map){
		T t = null;
		try {
			t = klass.newInstance();
			List<Field> fields = getInheritedPrivateFields(t.getClass());

			for (Field f : fields) {
				Object o = map.get(f.getName());
				if(o.getClass().equals(f.getType())){
					f.setAccessible(true);
					f.set(t, o);
				} else {
					//TODO: Log proper
					System.out.println("nao eh compativel");
				}				
			}
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			//TODO: Log proper
		}
		return t;
	}
	
	private static List<Field> getInheritedPrivateFields(Class<?> type) {
	    List<Field> result = new ArrayList<Field>();

	    Class<?> i = type;
	    while (i != null && i != Object.class) {
	        result.addAll(Arrays.asList(i.getDeclaredFields()));
	        i = i.getSuperclass();
	    }

	    return result;
	}
	

}
