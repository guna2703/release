package com.user.notes;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login-error").permitAll()
          .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout").permitAll()
          .and()
            .csrf().disable()
            .requestMatchers()
            .antMatchers("/index")
            .antMatchers("/login")

          .and()
            .authorizeRequests()
            .antMatchers("/welcome", "/registration")
            .permitAll()
            .anyRequest().authenticated();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource)
          .usersByUsernameQuery("select username, password from user_account where username=?");
    }
}