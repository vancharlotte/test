package com.example.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/*
@Configuration
@EnableResourceServer*/
public class ResourceServer/* extends ResourceServerConfigurerAdapter */{
  /*  @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/principal/**") // ### Specify path pattern that need OAuth authentication(Bearer auth) and authorization
                .authorizeRequests()
                .antMatchers("/").authenticated();
    }*/
}

