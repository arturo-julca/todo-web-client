package com.avantica.todowebclient.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class AppToken {

	String value;
	
	public AppToken(){		
	}
	
	public AppToken(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AppToken [value=" + value + "]";
	}
	
	
}
