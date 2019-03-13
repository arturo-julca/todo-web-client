package com.avantica.todowebclient.rest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.avantica.todowebclient.model.ToDo;

@Repository
public class TodoRestClient {

	public List<ToDo> getAllToDos() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<ToDo>> response = restTemplate.exchange(
				"http://localhost:9090/todo/",
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<ToDo>>() {});
		List<ToDo> listToDos = response.getBody();
		return listToDos;
	}
	
	public void addTodo(ToDo toDo){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				"http://localhost:9090/todo/", 
				toDo, 
				ToDo.class);
	}
}
