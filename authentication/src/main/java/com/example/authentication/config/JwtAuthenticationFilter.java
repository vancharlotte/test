package com.example.authentication.config;

import com.example.authentication.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("attemptAuthentication");

        Account account = null;
        try {
           account = new ObjectMapper().readValue(request.getInputStream(), Account.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                account.getUsername(),
                account.getPassword());
        System.out.println(usernamePasswordAuthenticationToken);

        return authenticationManager.authenticate(
                usernamePasswordAuthenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        System.out.println("successfulAuthentication");

        User user = (User) authentication.getPrincipal();
        // Create JWT Token

        String token = Jwts.builder()
                .setSubject(user.getUsername())
               // .withClaim("userId", ((MyUserDetails) authentication.getPrincipal()).getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("roles", user.getAuthorities())
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.SECRET)
                .compact();

        System.out.println( "token : " +  token);

        // Add token in response
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX +  token);
    }


}
