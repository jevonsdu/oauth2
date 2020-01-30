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
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableOAuth2Client
// @Configuration
public class Oauth2ClientApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Oauth2ClientApplication.class, args);
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate() {

		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		// The authenticated user's credentials is used as the params for username and
		// password for the token request via ResourceOwnerPasswordResourceDetails

		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();

		resourceDetails.setAccessTokenUri(String.format("%s/oauth/token", "http://localhost:8055"));
		resourceDetails.setClientId("qq");
		resourceDetails.setClientSecret("456");
		resourceDetails.setGrantType("password");
		// for password grant type
		resourceDetails.setUsername("user");
		resourceDetails.setPassword("123456");
		// fetch access_token by sending authentication data in HTTP Body
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.form);
		// send access_token via HTTP Header 'Bearer' field when accessing actual
		// service
		resourceDetails.setAuthenticationScheme(AuthenticationScheme.header);

		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
		oAuth2RestTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());

		return oAuth2RestTemplate;
	}

}
