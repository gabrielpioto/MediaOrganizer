package br.com.seriezer.rarbg;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainOcr {

	private static final String API_KEY = "e9c0ff16d906543b5cfd660883a60a84";
	private static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
//		URL url = new URL("http://api.newocr.com/v1/upload?key=" + API_KEY);
//		URLConnection conn = url.openConnection();
//		conn.setDoOutput(true);
		//teste2();
	}

	public static void teste() throws ClientProtocolException, IOException {
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://api.newocr.com/v1/upload?key=" + API_KEY);
		FileEntity fe = new FileEntity(new File("C:\\Users\\Gabriel\\Downloads\\teste.png"), ContentType.MULTIPART_FORM_DATA);
		post.setEntity(fe);
		System.out.println(client.execute(post));
	}
	
	public static String ocr(String strurl) throws ClientProtocolException, IOException, URISyntaxException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://api.newocr.com/v1/upload?key=" + API_KEY);
		strurl = strurl.replace("https://rarbg.to", "http://rarbg.com");
        //FileBody bin = new FileBody();
        //StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
        //File file = new File(new URL("http://rarbg.com/captcha2/tu8i0spzae23g46hforxwykq5mn9cvbd7.png"));
		URL url = new URL(strurl);
//		Files.copy(url.openStream(), Paths.get("C:\\Users\\Gabriel\\Downloads\\teste1.png"));
//		File file = new File("C:\\Users\\Gabriel\\Downloads\\teste1.png");
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", url.openStream(), ContentType.create("image/png"), "nome")
          //      .addPart("comment", comment)
                .build();
        


        httppost.setEntity(reqEntity);

        System.out.println("executing request " + httppost.getRequestLine());
        CloseableHttpResponse response = client.execute(httppost);
        
        try {
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
                System.out.println(resEntity);
                JsonNode node = mapper.readTree(EntityUtils.toString(resEntity));
                String temp = node.get("data").get("file_id").asText();
                
                HttpGet get = new HttpGet("http://api.newocr.com/v1/ocr?key="+API_KEY+"&file_id="+temp+"&page=1&lang=eng&psm=3");
                response = client.execute(get);
                resEntity = response.getEntity();
                
                node = mapper.readTree(EntityUtils.toString(resEntity));
                temp = node.get("data").get("text").asText();
                
                return temp;
            }
            EntityUtils.consume(resEntity);
        } finally {
            response.close();
        }
        return "";
	}
}
