package com.example.demoRest;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

public class ServerSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth.inMemoryAuthentication()
          .withUser("john").password("123").roles("USER");
    }
 
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() 
      throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll();
    }
    @Bean
    public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost/test?ssl=require ; ");
	    dataSource.setUsername("root");
	    dataSource.setPassword("");
	    return dataSource;
    }
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }
    
    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(
          "http://localhost:8081/spring-security-oauth-server/oauth/token");
        tokenService.setClientId("fooClientIdPassword");
        tokenService.setClientSecret("secret");
        return tokenService;
    }
}
