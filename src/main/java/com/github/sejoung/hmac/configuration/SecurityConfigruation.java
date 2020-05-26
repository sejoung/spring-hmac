package com.github.sejoung.hmac.configuration;

import com.github.sejoung.hmac.filter.HmacFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigruation extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}user").roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(
            HttpMethod.GET, "**").hasAnyRole("USER").and().formLogin()
            .disable().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling()
            .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
            .accessDeniedHandler(new AccessDeniedHandlerImpl()).and()
            .addFilterBefore(new HmacFilter("123", this.authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);


    }

}
