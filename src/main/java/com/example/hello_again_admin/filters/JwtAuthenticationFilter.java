package com.example.hello_again_admin.filters;

import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.hello_again_admin.util.JwtUtils;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private ServletContext context;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int frontendPortNum = 5173;
        String redirectUrl = "/login";

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::" + uri);

        String authHeader = req.getHeader("Authorization");

        String redirect = String.format("%s://%s:%d%s", scheme, serverName, frontendPortNum, redirectUrl);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.sendRedirect(redirect);
            return;
        }

        String token = authHeader.substring(7);
        String username = JwtUtils.extractUsername(token);

        if (!JwtUtils.validateToken(token, username)) {
            res.sendRedirect(redirect);
            return;
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, null);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
