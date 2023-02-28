package com.mediazer.base.api;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AbstractApi {
	
	private String rootUrl;
	
	public AbstractApi(String rootUrl){
		this.rootUrl = rootUrl;	
	}
	
	private URLConnection getConnection(ApiMethod method, Object... values) throws IOException{
		if(values.length < method.getMinParams() || values.length > method.getMaxParams())
			throw new IllegalArgumentException();
		ArrayList<Integer> headerIndex = new ArrayList<>(values.length);
		boolean first = true;
		String url = method.getBaseUrl();
		int k = method.getStartAt();
		
		for(int i = k;i<values.length;i++){
			ApiParam param = method.getParam(i-k);
			if(values[i]!=null){
				if(param.getValueType().isInstance(values[i])){
					switch(param.getParamType()){
					case HEADER:
						headerIndex.add(i);
						break;
					case URL:
						url+=(first?"?":"&")+param.getName()+"=%s";
						first = false;
						break;
					default:
						break;
					}
				} else {
					System.out.println("PAM! TIPO NA BATE");
					//TODO: throw an exception value type dont match
				}
			} else {
				//TODO: if still is a required param throw an exception
			}
		}
		url = String.format(rootUrl+url, clean(values));
		System.out.println(url);
		URLConnection conn = new URL(url).openConnection();
		for(Integer i : headerIndex){
			String key = method.getParam(i-k).getName();
			String value = values[i].toString();
			conn.addRequestProperty(key, value);
		}
		return conn;
	}
		
	protected <T> T get(ApiMethod method, Object... values) throws IOException {
		URLConnection conn = getConnection(method, values);
		return method.parse(conn.getInputStream());
		//return mapper.readValue(conn.getInputStream(), method.getType());
		
	}
	
	public <T> T post(ApiMethod method, String postBody, Object... values){
		return null;
	}
	
	public static Object[] clean(final Object[] v) {
	    ArrayList<Object> list = new ArrayList<>(Arrays.asList(v));
	    list.removeAll(Collections.singleton(null));
	    return list.toArray();
	}
	
	

}
