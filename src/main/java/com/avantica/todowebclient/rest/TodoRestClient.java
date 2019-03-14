package com.avantica.todowebclient.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.avantica.todowebclient.model.AppToken;
import com.avantica.todowebclient.model.ToDo;

@Repository
public class TodoRestClient {
	
	@Value("${reminder.server.url}")
	private String SERVICE_URL;
	
	@Autowired
	private AppToken appToken;
	
	public List<ToDo> getAllToDos() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<ToDo>> response = restTemplate.exchange(
				SERVICE_URL,
				HttpMethod.GET, 
				getHttpEntity(null), 
				new ParameterizedTypeReference<List<ToDo>>() {});
		List<ToDo> listToDos = response.getBody();
		return listToDos;
	}
	
	public ToDo getToDo(long id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ToDo> response = restTemplate.exchange(
				SERVICE_URL + "/" + id,
				HttpMethod.GET, 
				getHttpEntity(null), 
				new ParameterizedTypeReference<ToDo>() {});
		ToDo todo = response.getBody();
		return todo;
	}
	
	public void addTodo(ToDo toDo){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange(
				SERVICE_URL,
				HttpMethod.POST,
				getHttpEntity(toDo), 
				ToDo.class);
	}
	
	public void updateTodo(ToDo toDo){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange(
				SERVICE_URL,
				HttpMethod.PUT,
				getHttpEntity(toDo), 
				ToDo.class);
	}
	
	public void deleteTodo(Long id){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange(
				SERVICE_URL + "/" + id,
                HttpMethod.DELETE, 
                getHttpEntity(null), 
                new ParameterizedTypeReference<ToDo>() {});
	}
	
	private HttpEntity<?> getHttpEntity(ToDo toDo) {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + appToken.getValue());
        HttpEntity<?> entity;
        if(toDo!=null){
        	entity = new HttpEntity<>(toDo, headers);
        }else{
        	entity = new HttpEntity<>(headers);
        }        
        return entity;
    }
}
