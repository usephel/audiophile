package com.audiophileproject.usermanagement.security;

import com.audiophileproject.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** -- AUTHENTICATION MECHANISM
 * AuthProviderBean: with userDetailsService and UserDetails and password encoder
 * AuthTokenFilter: the actual OncePerRequestFilter: Validates the Jwt
 * configure filter chain:
 * cors, csrf, and endpoints
 * add authProvider to the HTTPSecurity
 * add the new AuthTokenFilter before UsernamePasswordAuthenticationFilter
 *
 */

@Configuration
@EnableMethodSecurity(
        prePostEnabled = true
)
@EnableWebSecurity
public class WebSecurityConfig {

    UserService userService;

    @Autowired
    WebSecurityConfig(UserService userService){
        this.userService = userService;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return  authProvider;
    }

    @Bean
    public AuthTokenFilter AuthenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // disable cors and csrf
    http.cors().and().csrf().disable()
            .authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated();
    // specify the auth provider
    http.authenticationProvider(authProvider());
    // add the filter
    http.addFilterBefore(AuthenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }




  // @Bean
  // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

  // return http
  //     .csrf(csrf -> csrf.disable())
  //     .authorizeHttpRequests(auth -> auth
  //         .requestMatchers("/token/**").permitAll()
  //         .anyRequest().authenticated()
  //     )
  // .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
  // .httpBasic(Customizer.withDefaults())
  // .build();

  // }
}
