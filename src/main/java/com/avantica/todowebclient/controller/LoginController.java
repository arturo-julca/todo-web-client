package com.avantica.todowebclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.avantica.todowebclient.model.AppToken;
import com.avantica.todowebclient.model.AppUser;
import com.avantica.todowebclient.rest.UserRestClient;
import com.avantica.todowebclient.util.HttpUtils;

@Controller
public class LoginController {
	
	@Autowired
	UserRestClient userRestClient;
	
	@Autowired
	AppToken appToken;

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("appUser", new AppUser());
        return modelAndView;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView Todo(AppUser appUser) {
		System.out.println(appUser);
		ResponseEntity<AppUser> response = userRestClient.login(appUser);
		String token = HttpUtils.getBearerToken(response.getHeaders());
		System.out.println("token:"+token);
		appToken.setValue(token);
		return new ModelAndView("redirect:/todo");
    }
	
}
