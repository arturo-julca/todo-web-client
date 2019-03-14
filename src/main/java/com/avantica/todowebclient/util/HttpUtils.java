package com.avantica.todowebclient.util;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

public class HttpUtils {

	private final static String AUTHORIZATION_HEADER = "Authorization";
	
	public static String getBearerToken(HttpHeaders headers){
		String bearerToken = null;
		for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
			String headerName = entry.getKey();
			System.out.println("headerName:"+headerName);
			if(entry.getKey().equalsIgnoreCase(AUTHORIZATION_HEADER)){
				bearerToken = entry.getValue().get(0).split(" ")[1];
			}
		}
		return bearerToken;
	}
}