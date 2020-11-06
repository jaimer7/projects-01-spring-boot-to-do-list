/* Responsible for the handling of user authorization */

package com.example.demo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configAuthentication (AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.passwordEncoder(new BCryptPasswordEncoder())
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT email, user_password, enabled FROM user WHERE email=?")
		.authoritiesByUsernameQuery("SELECT email, role FROM user WHERE email=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().permitAll();
	}
}
