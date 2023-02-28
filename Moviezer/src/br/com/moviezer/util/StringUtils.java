package br.com.moviezer.util;

import java.lang.reflect.InvocationTargetException;

public class StringUtils {

	public static String invokeFromCode(String code, Object bean)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, NullResultException {
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
						throw new NullResultException("get" + subPart
								+ " retornou nulo");
				}

				name += obj;
			}
		}
		return name;
	}

	@SuppressWarnings("serial")
	public static class NullResultException extends Exception {

		public NullResultException(String string) {
			super(string);
		}

	}

}
