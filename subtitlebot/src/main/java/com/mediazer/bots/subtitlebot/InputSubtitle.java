package com.mediazer.bots.subtitlebot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.ws.commons.util.Base64;

import com.mediazer.osub.model.OSubtitle;
import com.mediazer.osub.util.GZip;

import retrofit.mime.TypedOutput;

public class InputSubtitle implements TypedOutput {

	private String fileName;
	private OSubtitle subtitle;
	
	public InputSubtitle(OSubtitle subtitle, String fileName) {
		this.subtitle = subtitle;
		this.fileName = fileName;
	}

	@Override
	public String fileName() {
		return fileName;
	}

	@Override
	public String mimeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long length() {
		return -1; //unknown
	}

	@Override
	public void writeTo(OutputStream out) throws IOException {
		GZip.unzip(new ByteArrayInputStream(Base64.decode(subtitle.getData())), out);		
	}

}
