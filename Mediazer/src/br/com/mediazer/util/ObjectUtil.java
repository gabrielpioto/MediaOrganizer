package br.com.mediazer.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ObjectUtil {

	public static String invokeFromCode(String code, Object bean)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException,
			NullResultException {
		if (bean == null)
			return null;

		String[] parts = code.split("%");
		String name = "";
		for (int i = 0; i < parts.length; i++) {
			if (i % 2 == 0)
				name += parts[i];
			else {
				String[] subParts = parts[i].split("\\.");
				Object obj = bean;
				for (String subPart : subParts) {

					obj = obj.getClass().getMethod("get" + subPart).invoke(obj);
					if (obj == null)
						throw new NullResultException("get" + subPart + " retornou nulo");
				}

				name += obj;
			}
		}
		return name;
	}
	
	public static <T> void split(List<T> list, int limit, Consumer<List<T>> consumer){
		int i, j;
		int splitSize = list.size() / limit + 1;
		int factor = list.size() / splitSize + 1;
		int mod = list.size() % splitSize;

		for (i = 1, j = 0; i < splitSize; i++, j += factor)
			consumer.accept(list.subList(j, factor + j));
		consumer.accept(list.subList(j, factor + j - splitSize + mod));	
	}

	@SuppressWarnings("serial")
	public static class NullResultException extends Exception {

		public NullResultException(String string) {
			super(string);
		}

	}

}
