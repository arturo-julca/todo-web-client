package com.avantica.todowebclient.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.avantica.todowebclient.model.ToDo;
import com.avantica.todowebclient.rest.TodoRestClient;

@Controller
public class ToDoWebController {
	
	@Autowired
	TodoRestClient todoRestClient;

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ModelAndView listToDos() {
        ModelAndView modelAndView = new ModelAndView("todo_list");

        List<ToDo> listToDos = todoRestClient.getAllToDos();
//        	List<ToDo> listToDos = new ArrayList<ToDo>();
//        listToDos.add(new ToDo(1L, "first todo", LocalDateTime.now(), "test1@email.com"));
//        listToDos.add(new ToDo(2L, "second todo", LocalDateTime.now(), "test2@email.com"));
//        listToDos.add(new ToDo(3L, "third todo", LocalDateTime.now(), "test3@email.com"));
        
        modelAndView.addObject("listToDos", listToDos);
        return modelAndView;
    }
	
	@RequestMapping(value = "/todo/add", method = RequestMethod.GET)
    public ModelAndView openAddToDo() {
		ModelAndView modelAndView = new ModelAndView("todo_add");
		modelAndView.addObject("toDo", new ToDo());
        return modelAndView;
    }

	//public String addTodo(@Valid ToDo toDo, BindingResult result, Model model) {
//	@RequestMapping(value = "/todo/add", method = RequestMethod.POST)
//	public String Todo(ToDo toDo) {
//        System.out.println(toDo);
//		return "redirect:/todo";
//    }
	@RequestMapping(value = "/todo/add", method = RequestMethod.POST)
	public ModelAndView Todo(ToDo toDo) {
		todoRestClient.addTodo(toDo);
		return new ModelAndView("redirect:/todo");
    }
	
	@RequestMapping(path = "/todo/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProduct(Model model, @PathVariable(value = "id") String id) {
        //model.addAttribute("product", productRepository.findOne(id));
		ModelAndView modelAndView = new ModelAndView("todo_edit");		
        modelAndView.addObject("toDo", new ToDo(1L, "first todo", LocalDateTime.now(), "test1@email.com"));
        return modelAndView;
    }

    @RequestMapping(path = "/todo/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable(name = "id") String id) {
        //productRepository.delete(id);
        return new ModelAndView("redirect:/todo");
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listToDos2() {
    	RestTemplate restTemplate = new RestTemplate();
    	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjIiLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTU1MjUwMTY5NywiZXhwIjoxNTUyNTg4MDk3fQ.UwD7RZ1QrsqbxrlZ6pyglr9ABPVelnbZ22U1waqeGCQXl6XDAFheBhzMiURYhgNlkThx0b_Zg6bhJFT179Pnwg";
    	System.out.println("=========test===========");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<List<ToDo>> response = restTemplate.exchange(
                "http://localhost:8762/reminder/todo",
                HttpMethod.GET, 
                entity, 
                new ParameterizedTypeReference<List<ToDo>>() {});

        
        
        ModelAndView modelAndView = new ModelAndView("todo_list");

        List<ToDo> listToDos = response.getBody();
        modelAndView.addObject("listToDos", listToDos);
        return modelAndView;
    }
}
