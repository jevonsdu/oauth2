package com.zz;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class MySTSServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("pp").secret("{noop}123").scopes("my_read", "my_write")
			.resourceIds("Service01")
			.authorizedGrantTypes("client_credentials")
			.and()
			.withClient("qq").secret("{noop}456").scopes("execute")
			.resourceIds("Service02")
			.authorizedGrantTypes("authorization_code", "password")
			.accessTokenValiditySeconds(1200).refreshTokenValiditySeconds(50000);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()") // allow check token
			.allowFormAuthenticationForClients();
	}

}
