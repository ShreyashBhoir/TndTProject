package com.maxxton.tour.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.maxxton.tour.service.CustomerUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";

    @Autowired
    private CustomerUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                //.disable()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers("/user/user/booking/**").hasAnyRole(USER,ADMIN)
    			.antMatchers(HttpMethod.POST,"/user/user/booking/addBooking").hasAnyRole(ADMIN, USER)
    			.antMatchers(HttpMethod.PUT,"/user/user/booking/updateBooking/bookingdate/**").hasAnyRole(ADMIN, USER)
    			.antMatchers(HttpMethod.PUT,"/user/user/booking/updateBooking/groupsize/**").hasAnyRole(ADMIN, USER)
    			.antMatchers(HttpMethod.DELETE,"/user/user/booking/deleteBooking/**").hasAnyRole(ADMIN, USER)
    			.antMatchers("/user/admin/getTours").permitAll()
    			.antMatchers("/user/admin/getTopTours").permitAll()
    			.antMatchers("/user/admin/getTourById/{id}").permitAll()
    			.antMatchers("/review/**").permitAll()
    			.antMatchers(HttpMethod.POST,"/review/addReview/{tourid}").hasAnyRole(ADMIN, USER)
    			.antMatchers(HttpMethod.PUT,"/review/updatereview/{tourId}/{reviewId}").hasRole(USER)
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    //over
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}