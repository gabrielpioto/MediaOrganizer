package br.com.moviezer.model.move;

import java.util.function.Predicate;

public enum SequelMode {
	INSIDE(s -> s.endsWith("0")), 
	MIDDLESIDE(s -> s.length() > 1	&& s.charAt(1) == '0'), 
	OUTSIDE(s -> s.startsWith("0")), 
	NONE(s -> !s.contains("0"));

	private Predicate<String> p;

	private SequelMode(Predicate<String> p) {
		this.p = p;
	}

	public static SequelMode get(String s) {
		for (SequelMode value : values()) {
			if (value.p.test(s))
				return value;
		}
		return null;
	}
}
