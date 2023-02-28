package com.mediazer.tvdb.enums;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Element;

import com.mediazer.base.api.ApiMethod;
import com.mediazer.base.api.ApiParam;
import com.mediazer.tvdb.model.wrappers.Languages;

public enum StaticMethod implements ApiMethod {
	LANGUAGES(Languages.class);

	;

	private Class<?> docClass;
	private String baseUrl;

	private StaticMethod(Class<?> docClass) {
		this.docClass = docClass;
		baseUrl = "/%s/" + name().toLowerCase() + ".xml";
	}

	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	@Override
	public ApiParam getParam(int i) {
		return null;
	}

	@Override
	public int getStartAt() {
		return 1;
	}

	@Override
	public int getMaxParams() {
		return 1;
	}

	@Override
	public int getMinParams() {
		return 1;
	}

	@Override
	public <T> T parse(InputStream stream) throws IOException {
		T result = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(docClass);
			Unmarshaller u = jc.createUnmarshaller();
			result = (T) u.unmarshal(stream);

		} catch (JAXBException e) {
			throw new IOException(e);
		}
		return result;
	}

}
