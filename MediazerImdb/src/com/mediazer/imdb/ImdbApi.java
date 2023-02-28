package com.mediazer.imdb;

import java.io.IOException;
import java.util.Locale;

import com.mediazer.base.api.AbstractApi;
import com.mediazer.imdb.enums.TitleMethod;
import com.mediazer.imdb.model.TitleDetails;
import com.mediazer.imdb.wrappers.WrapperData;

public class ImdbApi extends AbstractApi {

	public ImdbApi() {
		super("http://app.imdb.com");
	}
	
	public TitleDetails getTitleDetails(String imdbId, Locale locale){
		WrapperData<TitleDetails> data = null;
		try{
			data = get(TitleMethod.MAINDETAILS, imdbId, locale);
		}catch(IOException e){
			e.printStackTrace();
		}
		return data.getData();		
	}

}
