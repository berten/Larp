package org.deschutter.omen.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"org.deschutter"}, excludeFilters = {
        @ComponentScan.Filter(value = Repository.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Component.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Service.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Configuration.class)})
public class Security extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/css/**", "/img/**", "/js/**"); // #3
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll().and().httpBasic().and().authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/epos/**").hasRole("USER").anyRequest()
                .authenticated();
    }
}
