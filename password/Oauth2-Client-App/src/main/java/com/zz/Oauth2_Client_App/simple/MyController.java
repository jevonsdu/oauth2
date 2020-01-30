package com.zz.Oauth2_Client_App.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@Autowired
	private OAuth2RestOperations restOperations;

	@GetMapping("/hi")
	public String test() {
		
		ResponseEntity<String> responseEntity = restOperations.getForEntity("http://localhost:8081/users/hello",
				String.class);
	
		return responseEntity.getBody();

	}
}
