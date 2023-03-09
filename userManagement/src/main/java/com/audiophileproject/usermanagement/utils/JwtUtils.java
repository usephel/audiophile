package com.audiophileproject.usermanagement.utils;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.WebUtils;


import java.net.http.HttpRequest;

public class JwtUtils {

    @Value("${audiophile.app.jwtSecret}")
    private String jwtSecret;

    @Value("${audiophile.app.jwtExpirationMS}")
    private int jwtExpirationMS;

    @Value("${audiophile.app.jwtCookieName}")
    private String jwtCookieName;

    public String getJwtFromCookies(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request,jwtCookieName);
        if(cookie != null){
            return cookie.getValue();
        }else return  null;
    }

    public boolean validateJwt(String jwtToken){
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(jwtToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {}"+ e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {}"+ e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {}"+ e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}"+ e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}"+ e.getMessage());
        }

        return false;
    }

    public String getUsernameFromJwt(String jwt){
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(jwt).getBody().getSubject();
    }

}
