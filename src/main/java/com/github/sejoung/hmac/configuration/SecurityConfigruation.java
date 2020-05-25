package com.github.sejoung.hmac.configuration;

import com.github.sejoung.hmac.filter.HmacFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfigruation extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilter(new HmacFilter("123"));
    }
}
