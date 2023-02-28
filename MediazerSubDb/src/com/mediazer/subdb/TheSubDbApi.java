package com.mediazer.subdb;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class TheSubDbApi {

	private static final String userAgentTemplate = "SubDB/1.0 (%s/%s; %s)";
	private static final String downloadUrl = "http://api.thesubdb.com/?action=download&hash=%s&language=%s";

	private HttpClient client;
	private HttpGet get;

	public TheSubDbApi(String name, String version, String url) {
		String userAgent = String.format(userAgentTemplate, name, version, url);
		client = HttpClients.createDefault();
		get = new HttpGet();
		get.setHeader("User-Agent", userAgent);
	}

	public synchronized boolean downloadSubtitle(String hash, String language, OutputStream writeTo)
			throws IOException, URISyntaxException {
		System.out.println("oi galere");
		get.setURI(new URI(String.format(downloadUrl, hash, language)));
		HttpResponse resp = client.execute(get);
		System.out.println(resp);
		if (resp.getStatusLine().getStatusCode() == 404) {
			return false;
		}
		resp.getEntity().writeTo(writeTo);
		return true;
	}

	public static class Hasher {
		private static final int HASH_CHUNK_SIZE = 64 * 1024;

		public static String computeHash(Path file) {
			FileChannel fileChannel = null;
			ByteBuffer headBuffer = ByteBuffer.allocate(HASH_CHUNK_SIZE);
			ByteBuffer tailBuffer = ByteBuffer.allocate(HASH_CHUNK_SIZE);
			String r = null;
			try {
				fileChannel = FileChannel.open(file, StandardOpenOption.READ);
				long size = fileChannel.size();
				// long chunkSizeForFile = Math.min(HASH_CHUNK_SIZE, size);

				fileChannel.read(headBuffer);
				fileChannel.position(Math.max(size - HASH_CHUNK_SIZE, 0));
				fileChannel.read(tailBuffer);
				byte[] head = headBuffer.array();
				byte[] tail = tailBuffer.array();

				head = Arrays.copyOf(head, 2 * HASH_CHUNK_SIZE);
				System.arraycopy(tail, 0, head, HASH_CHUNK_SIZE, HASH_CHUNK_SIZE);
				r = DigestUtils.md5Hex(head);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fileChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return r;
		}
	}

}
