package com.mediazer.osub.conversor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mediazer.osub.model.OMovie;
import com.mediazer.osub.model.base.OObject;
import com.mediazer.osub.model.response.OMovies;

public class Conversors {
	
	public static final Conversor<?, Map<String,?>> DEFAULT = new DefaultConversor<>();	
	
	
	private static class DefaultConversor<T> implements Conversor<T, Map<String,?>>{
		@Override
		public T convert(Class<T> klass, Map<String, ?> data){
			T t = null;
			try {
				t = klass.newInstance();
				List<Field> fields = getInheritedPrivateFields(t.getClass());

				for (Field f : fields) {
					Object o = data.get(f.getName());
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
	
	private static class MoviesConversor extends DefaultConversor<OMovies>{
		@Override
		public OMovies convert(Class<OMovies> klass, Map<String, ?> map) {
			OMovies movies = super.convert(klass, map); 
			Object obj;
			Map<String, OMovie> ndata = null;
			if ((obj = map.get("data")) instanceof Map) {
				Map<String, ?> data = (Map<String, ?>) obj;
				ndata = new HashMap<>();
				for (Entry<String, ?> entry : data.entrySet()) {
					OMovie movie = null;
					if((obj = entry.getValue()) instanceof Map){
						Map<String, String> movieMap = (Map<String, String>) obj;
						movie = new OMovie(movieMap);
					}
					ndata.put(entry.getKey(), movie);
				}
			} else {
				System.out.println("nada encontrado");
				//TODO: Log proper
			}
			
			movies.data = ndata;
			return movies;
		}
	}

}
