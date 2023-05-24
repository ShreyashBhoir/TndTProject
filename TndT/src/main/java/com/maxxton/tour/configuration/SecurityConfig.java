/*
  //package com.maxxton.tour.configuration; // //import
  org.hibernate.bytecode.enhance.internal.tracker.NoopCollectionTracker;
  //import org.springframework.beans.factory.annotation.Autowired; //import
  org.springframework.context.annotation.Bean; //import
  org.springframework.context.annotation.Configuration; //import
  org.springframework.http.HttpMethod; //import
  org.springframework.security.authentication.AuthenticationProvider; //import
  org.springframework.security.authentication.dao.DaoAuthenticationProvider;
  //import
  org.springframework.security.config.annotation.authentication.builders.
  AuthenticationManagerBuilder; //import
  org.springframework.security.config.annotation.web.builders.HttpSecurity;
  //import org.springframework.security.config.annotation.web.configuration.
  EnableWebSecurity; //import
  org.springframework.security.config.annotation.web.configuration.
  WebSecurityConfigurerAdapter; //import
  org.springframework.security.core.userdetails.UserDetailsService; //import
  org.springframework.security.crypto.password.NoOpPasswordEncoder; //
  //@EnableWebSecurity //@Configuration //public class SecurityConfig extends
  WebSecurityConfigurerAdapter { // private static final String USER = "USER";
  // private static final String ADMIN = "ADMIN"; // // @Autowired // private
  UserDetailsService userDetailService; // // /* // * @Override protected void
  configure(AuthenticationManagerBuilder auth) throws // * Exception {
  System.out.println("hello"); // * auth.userDetailsService(userDetailService);
  } //
 
//	@Bean
//	public AuthenticationProvider auth() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailService);
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		return provider;
//
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.csrf().disable().authorizeHttpRequests()
//					.antMatchers("/user/user/booking/**").hasAnyRole(USER, ADMIN)
//					.antMatchers(HttpMethod.POST, "/user/user/booking/addBooking").hasAnyRole(ADMIN, USER)
//					.antMatchers(HttpMethod.PUT, "/user/user/booking/updateBooking/bookingdate/**").hasAnyRole(ADMIN, USER)
//					.antMatchers(HttpMethod.PUT, "/user/user/booking/updateBooking/groupsize/**").hasAnyRole(ADMIN, USER)
//					.antMatchers(HttpMethod.DELETE, "/user/user/booking/deleteBooking/**").hasAnyRole(ADMIN, USER)
//					.and()
//					.httpBasic();
//	}
//}*/