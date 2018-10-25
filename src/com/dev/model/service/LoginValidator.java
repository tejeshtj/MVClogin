package com.dev.model.service;

import java.time.Instant;

import com.dev.model.beans.Credential;

public class LoginValidator {

	Credential cred=new Credential();
	
	public void countntime(int count) {
		
		++count;
		cred.setCount(count);
		Instant time=Instant.now();
		cred.setStart(time);
		
	}

}
