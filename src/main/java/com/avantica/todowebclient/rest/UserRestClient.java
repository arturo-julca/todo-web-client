package com.avantica.todowebclient.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.avantica.todowebclient.model.AppUser;

@Repository
public class UserRestClient {

	@Value("${auth.server.url}")
	private String SERVICE_URL;
	
	public ResponseEntity<AppUser> login(AppUser appUser) {		
		ResponseEntity<AppUser> response = null;
		int count = 0, MAX_TRIES = 3;
		while(count++ < MAX_TRIES){
			try{
				response = postAppUser(appUser);
				if(response.getStatusCode().equals(HttpStatus.OK)){
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return response;
	}
	
	private HttpEntity<?> getHttpEntity(AppUser appUser) {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(appUser, headers);   
        return entity;
    }
	
	private ResponseEntity<AppUser> postAppUser(AppUser appUser){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(
				SERVICE_URL,
				HttpMethod.POST, 
				getHttpEntity(appUser), 
				new ParameterizedTypeReference<AppUser>() {});
	}
}
