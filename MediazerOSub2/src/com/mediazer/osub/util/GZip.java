package com.mediazer.osub.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

public class GZip {

	public static void unzip(InputStream is, OutputStream os) throws IOException {
		GZIPInputStream gis = new GZIPInputStream(is);
		OutputStream out = new BufferedOutputStream(os);
		byte[] buffer = new byte[1024];
		for (;;) {
			synchronized (buffer) {
				int amountRead = gis.read(buffer);
				if (amountRead == -1) {
					break;
				}
				out.write(buffer, 0, amountRead);
			}
		}
		out.flush();
		out.close();
	}
}
