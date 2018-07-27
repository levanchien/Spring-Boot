/*
package com.lvc.springbootrestjpabasicauth.config;

import com.lvc.springbootrestjpabasicauth.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/","/login").permitAll();

        http.authorizeRequests()
            .antMatchers("/user")
            .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        http.authorizeRequests()
            .antMatchers("/admin")
            .access("hasAnyRole('ROLE_ADMIN')");

        http.authorizeRequests()
            .and()
            .exceptionHandling()
            .accessDeniedPage("/403");

        http.authorizeRequests()
            .and()
            .formLogin()
            .loginProcessingUrl("/login")
            .failureUrl("/loginPage")
            .usernameParameter("username")
            .passwordParameter("password");

        */
/* Remember me *//*

        http.authorizeRequests()
            .and()
            .rememberMe()
            .tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(60 * 30);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
*/
