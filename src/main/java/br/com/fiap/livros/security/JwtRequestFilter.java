package br.com.fiap.livros.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserService jwtUserService;

    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, JwtUserService jwtUserService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserService = jwtUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");

        if(requestToken == null || !requestToken.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Token");
        }

        String username = jwtTokenUtil.getUserName(requestToken);

        if(username != null) {
            UserDetails userDetails = jwtUserService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, 
                                                                                    null, 
                                                                                    userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}

