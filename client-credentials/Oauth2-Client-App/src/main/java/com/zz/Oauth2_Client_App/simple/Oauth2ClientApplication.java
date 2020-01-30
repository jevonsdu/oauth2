/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zz.Oauth2_Client_App.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableOAuth2Client
public class Oauth2ClientApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Oauth2ClientApplication.class, args);
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate() {

		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(String.format("%s/oauth/token", "http://localhost:8055"));
		resourceDetails.setClientId("pp");
		resourceDetails.setClientSecret("123");
		resourceDetails.setGrantType("client_credentials");

	    DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
	    
	    return new OAuth2RestTemplate(resourceDetails, clientContext);
	}
}
