package com.avantica.todowebclient.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.avantica.todowebclient.model.ToDo;

@Repository
public class TodoRestClient {

	@Autowired
	private Environment env;
	
	@Value("${todo.server.url}")
	private String SERVICE_URL;
	
	public List<ToDo> getAllToDos() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<ToDo>> response = restTemplate.exchange(
				SERVICE_URL,
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<ToDo>>() {});
		List<ToDo> listToDos = response.getBody();
		return listToDos;
	}
	
	public ToDo getToDo(long id) {
		RestTemplate restTemplate = new RestTemplate();
		ToDo toDo = restTemplate.getForObject(
				SERVICE_URL + "/" + id, 
				ToDo.class);		
		return toDo;
	}
	
	public void addTodo(ToDo toDo){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				SERVICE_URL, 
				toDo, 
				ToDo.class);
	}
	
	public void updateTodo(ToDo toDo){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<ToDo> requestUpdate = new HttpEntity<>(toDo, headers);
		restTemplate.exchange(
				SERVICE_URL,
				HttpMethod.PUT,
				requestUpdate, 
				ToDo.class);
	}
	
	public void deleteTodo(Long id){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(
				SERVICE_URL + "/" + id);
	}
}
