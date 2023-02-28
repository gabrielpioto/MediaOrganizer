package br.com.moviezer.model.move;

import java.util.function.Predicate;

public enum GenreMode {
	NO_GENRE_2(s->!s.contains("2")), 
	BOTH_GENRES(s->s.contains("12")), 
	TREE_GENRE(s->s.contains("-2"));

	private Predicate<String> p;

	private GenreMode(Predicate<String> p) {
		this.p = p;
	}

	public static GenreMode get(String code) {
		for (GenreMode value : values()) {
			if (value.p.test(code))
				return value;
		}
		return null;
	}
}
