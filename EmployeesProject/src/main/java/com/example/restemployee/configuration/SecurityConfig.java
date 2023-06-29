package com.example.restemployee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
               "select user_id , pw ,active from members where user_id = ?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id , role from roles where user_id = ?"
        );
        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests( configurer ->
                configurer
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers("/api/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/api/showAddForm/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
        )

                .formLogin(form ->
                        form
                                .loginPage("/myLoginForm")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll()
                                .logoutSuccessUrl("/"))
                .exceptionHandling(configurer->
                        configurer
                                .accessDeniedPage("/access-denied")

                );


        return  http.build();
    }


}
