package com.audiophileproject.usermanagement.security;

import com.audiophileproject.usermanagement.models.User;
import com.audiophileproject.usermanagement.services.UserService;
import com.audiophileproject.usermanagement.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.Authenticator;

public class AuthTokenFilter extends OncePerRequestFilter {

    JwtUtils jwtUtils;
    UserService userService;

    @Autowired
    public AuthTokenFilter(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    public  AuthTokenFilter(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = jwtUtils.getJwtFromCookies(request);
            if(jwt != null && jwtUtils.validateJwt(jwt)){
                String username = jwtUtils.getUsernameFromJwt(jwt);
                User user = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(authentication));
            }
        }

    }
}
