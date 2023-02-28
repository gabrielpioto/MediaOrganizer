package com.mediazer.osub.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mediazer.osub.model.base.OObject;

public class OSubInfo extends OObject{
	private String MatchedBy, IDSubMovieFile, MovieHash, MovieByteSize, MovieTimeMS,
			IDSubtitleFile, SubFileName, SubActualCD, SubSize, SubHash, IDSubtitle,
			UserID, SubLanguageID, SubFormat, SubSumCD, SubAuthorComment, SubAddDate,
			SubBad, SubRating, SubDownloadsCnt, MovieReleaseName, IDMovie, IDMovieImdb,
			MovieName, MovieNameEng, MovieYear, MovieImdbRating, SubFeatured,
			UserNickName, ISO639, LanguageName, SubComments, SubHearingImpaired, UserRank,
			SeriesSeason, SeriesEpisode, MovieKind, SubDownloadLink, ZipDownloadLink,
			SubtitlesLink;

	public String getMatchedBy() {
		return MatchedBy;
	}

	public String getIDSubMovieFile() {
		return IDSubMovieFile;
	}

	public String getMovieHash() {
		return MovieHash;
	}

	public String getMovieByteSize() {
		return MovieByteSize;
	}

	public String getMovieTimeMS() {
		return MovieTimeMS;
	}

	public String getIDSubtitleFile() {
		return IDSubtitleFile;
	}

	public String getSubFileName() {
		return SubFileName;
	}

	public String getSubActualCD() {
		return SubActualCD;
	}

	public String getSubSize() {
		return SubSize;
	}

	public String getSubHash() {
		return SubHash;
	}

	public String getIDSubtitle() {
		return IDSubtitle;
	}

	public String getUserID() {
		return UserID;
	}

	public String getSubLanguageID() {
		return SubLanguageID;
	}

	public String getSubFormat() {
		return SubFormat;
	}

	public String getSubSumCD() {
		return SubSumCD;
	}

	public String getSubAuthorComment() {
		return SubAuthorComment;
	}

	public String getSubAddDate() {
		return SubAddDate;
	}

	public String getSubBad() {
		return SubBad;
	}

	public String getSubRating() {
		return SubRating;
	}

	public String getSubDownloadsCnt() {
		return SubDownloadsCnt;
	}

	public String getMovieReleaseName() {
		return MovieReleaseName;
	}

	public String getIDMovie() {
		return IDMovie;
	}

	public String getIDMovieImdb() {
		return IDMovieImdb;
	}

	public String getMovieName() {
		return MovieName;
	}

	public String getMovieNameEng() {
		return MovieNameEng;
	}

	public String getMovieYear() {
		return MovieYear;
	}

	public String getMovieImdbRating() {
		return MovieImdbRating;
	}

	public String getSubFeatured() {
		return SubFeatured;
	}

	public String getUserNickName() {
		return UserNickName;
	}

	public String getISO639() {
		return ISO639;
	}

	public String getLanguageName() {
		return LanguageName;
	}

	public String getSubComments() {
		return SubComments;
	}

	public String getSubHearingImpaired() {
		return SubHearingImpaired;
	}

	public String getUserRank() {
		return UserRank;
	}

	public String getSeriesSeason() {
		return SeriesSeason;
	}

	public String getSeriesEpisode() {
		return SeriesEpisode;
	}

	public String getMovieKind() {
		return MovieKind;
	}

	public String getSubDownloadLink() {
		return SubDownloadLink;
	}

	public String getZipDownloadLink() {
		return ZipDownloadLink;
	}

	public String getSubtitlesLink() {
		return SubtitlesLink;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_FIELD_NAMES_STYLE);
		//return super.toString();
	}

}
