package com.lcv.springsecurity.config;

import com.lcv.springsecurity.filters.JWTAuthenticationFilter;
import com.lcv.springsecurity.filters.JWTLoginFilter;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("PASSWORD:" + passwordEncoder().encode("123"));
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
        auth.jdbcAuthentication().dataSource(InstanceDatasource.dataSource())
                .usersByUsernameQuery("SELECT sLogin as username, sPassword as password, enabled from [User] WHERE sLogin = ?")
                .authoritiesByUsernameQuery("SELECT sLogin AS username, nRole_Id AS role FROM [User] WHERE sLogin = ?");
    }


}

class InstanceDatasource {

    static DataSource dataSource() {
        return DataSourceBuilder.create()
                .username("sa")
                .password("123qwe!@#QWE")
                .url("jdbc:sqlserver://localhost;databaseName=iMuzik")
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .build();
    }
}

