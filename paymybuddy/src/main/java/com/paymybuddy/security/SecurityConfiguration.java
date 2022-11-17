package com.paymybuddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.paymybuddy.service.UserService;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
    UserService userService;
    
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new MyUserDetailsService();
//    }
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable()
    	.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login", "/create/account")
        .permitAll()
        .anyRequest()
        .authenticated();

        return http.build();
    }
    
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//    	return auth.userDetailsService(userService).and().build();    
//    }
//    
    
    
//    @Bean
//    public UserDetails userDetailsService(String email, String password) {
//    	System.out.println("UserDetailsService");
//    	com.paymybuddy.entity.User user = userRepository.findByEmailAndPassword(email, password);
//
//        return new UserPrincipal(user);
//    	
//    }
    
//    @Bean
//	public UserPrincipal authSuccess(String email, String password) {
//    	com.paymybuddy.entity.User user = userRepository.findByEmailAndPassword(email, password);
//
//        return new UserPrincipal(user);
//	}
 
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
//    }
 
}
