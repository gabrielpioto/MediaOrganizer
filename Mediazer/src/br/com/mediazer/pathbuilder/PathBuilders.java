package br.com.mediazer.pathbuilder;

import br.com.mediazer.model.EpisodeDetails;
import br.com.mediazer.model.FilmDetails;
import br.com.mediazer.model.MediaDetails;
import br.com.mediazer.model.MediaDetails.MediaType;

public class PathBuilders {

	public static final PathBuilder<MediaDetails> DEFAULT = new PathBuilder<MediaDetails>()
			.If(d -> d.getType() == MediaType.EPISODE)
			.add(d -> "Season " + ((EpisodeDetails) d).getSeasonNumber())
			.add(d -> ((EpisodeDetails) d).getSerieTitle()).add(d -> "Series").Else()
			.If(d -> d.getType() == MediaType.FILM)
			.If(d -> ((FilmDetails) d).getCollection() == null)
			.add(d -> d.getGenres().get(0)).Else()
			.add(d -> ((FilmDetails) d).getCollection()).add(d -> "Coletaneas")
			.endElse().add(d -> "Filmes").Else().add(d -> "Outros").endElse().endElse()
			.add(d -> "Media");

	public static final PathBuilder<MediaDetails> SIMPLE = new PathBuilder<MediaDetails>()
			.If(d -> d.getType() == MediaType.EPISODE).add(d -> "Series").Else()
			.If(d -> d.getType() == MediaType.FILM).add(d -> "Movies").Else()
			.add(d -> "Others").endElse().endElse().add(d -> "Media");

}
