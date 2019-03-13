package com.avantica.todowebclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        modelAndView.addObject("listToDos", listToDos);
        return modelAndView;
    }
	
	@RequestMapping(value = "/todo/add", method = RequestMethod.GET)
    public ModelAndView openAddToDo() {
		ModelAndView modelAndView = new ModelAndView("todo_add");
		modelAndView.addObject("toDo", new ToDo());
        return modelAndView;
    }

	@RequestMapping(value = "/todo/add", method = RequestMethod.POST)
	public ModelAndView Todo(ToDo toDo) {
		todoRestClient.addTodo(toDo);
		return new ModelAndView("redirect:/todo");
    }
	
	@RequestMapping(path = "/todo/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProduct(Model model, @PathVariable(value = "id") String id) {
		ToDo toDo = todoRestClient.getToDo(Long.parseLong(id));
		ModelAndView modelAndView = new ModelAndView("todo_edit");		
        modelAndView.addObject("toDo", toDo);
        return modelAndView;
    }

    @RequestMapping(path = "/todo/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable(name = "id") String id) {
    	todoRestClient.deleteTodo(Long.parseLong(id));
        return new ModelAndView("redirect:/todo");
    }
    

}
