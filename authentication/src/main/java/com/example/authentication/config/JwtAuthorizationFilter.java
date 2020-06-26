package com.example.authentication.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // Read the Authorization header, where the JWT token should be
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        System.out.println("token :" + token);

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }



        // If header is present, parse token to find username
                Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getBody();


        String username = claims.getSubject();

        ArrayList<Map<String,String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");


        //de string a authorities
        List<GrantedAuthority> authorities = new ArrayList<>();
    roles.forEach(role ->{authorities.add(new SimpleGrantedAuthority(role.values().toString()));});
        System.out.println(authorities.toString());


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // Continue filter execution
        chain.doFilter(request, response);


        }
}
