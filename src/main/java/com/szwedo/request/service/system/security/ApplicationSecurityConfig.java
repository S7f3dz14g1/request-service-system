package com.szwedo.request.service.system.security;

import com.szwedo.request.service.system.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserService service;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests()
        .antMatchers(HttpMethod.POST, "/client/**").hasAuthority(ApplicationUserPermission.CLIENT_WRITE.getPermission())
        .antMatchers(HttpMethod.GET, "/client/**").hasAuthority(ApplicationUserPermission.CLIENT_READ.getPermission())
        .antMatchers(HttpMethod.POST, "/device/**").hasAuthority(ApplicationUserPermission.DEVICE_WRITE.getPermission())
        .antMatchers(HttpMethod.GET, "/device/**").hasAuthority(ApplicationUserPermission.DEVICE_WRITE.getPermission())
        .antMatchers(HttpMethod.POST, "/invoice/**").hasAuthority(ApplicationUserPermission.INVOICE_WRITE.getPermission())
        .antMatchers(HttpMethod.GET, "/invoice/**").hasAuthority(ApplicationUserPermission.INVOICE_READ.getPermission())
        .antMatchers(HttpMethod.PUT, "/order/invoice/**").hasAuthority(ApplicationUserPermission.ORDER_WRITE.getPermission())
        .antMatchers(HttpMethod.PUT, "/order/doneWork/**").hasAuthority(ApplicationUserPermission.ORDER_WRITE.getPermission())
        .antMatchers(HttpMethod.PUT, "/order/status/**").hasAuthority(ApplicationUserPermission.ORDER_WRITE.getPermission())
        .antMatchers(HttpMethod.PUT, "/order/technician/**").hasAuthority(ApplicationUserPermission.ORDER_WRITE.getPermission())
        .antMatchers(HttpMethod.GET, "/invoice/**").hasAuthority(ApplicationUserPermission.INVOICE_READ.getPermission())
        .antMatchers(HttpMethod.GET, "/order/**").hasAuthority(ApplicationUserPermission.ORDER_READ.getPermission())
        .antMatchers(HttpMethod.GET, "/user/**").hasAuthority(ApplicationUserPermission.ORDER_READ.getPermission())
        .antMatchers(HttpMethod.POST, "/user/**").hasAuthority(ApplicationUserPermission.USERS_WRITE.getPermission())
        .antMatchers(HttpMethod.GET, "/order/details/**").permitAll()
        .antMatchers(HttpMethod.POST, "/order/**").permitAll()

        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(service);
    return provider;
  }
}
