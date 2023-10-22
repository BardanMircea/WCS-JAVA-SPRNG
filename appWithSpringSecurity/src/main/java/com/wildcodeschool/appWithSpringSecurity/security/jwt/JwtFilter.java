package com.wildcodeschool.appWithSpringSecurity.security.jwt;

import com.wildcodeschool.appWithSpringSecurity.entity.Utilisateur;
import com.wildcodeschool.appWithSpringSecurity.security.UtilisateurDetailsService;
import com.wildcodeschool.appWithSpringSecurity.service.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UtilisateurDetailsService utilisateurDetailsService;

    public JwtFilter(@Autowired JwtService jwtService,
                     @Autowired UtilisateurDetailsService utilisateurDetailsService){
        this.jwtService = jwtService;
        this.utilisateurDetailsService = utilisateurDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Verify the requested path
        String reqPath = request.getRequestURI();

        // If it matches "/login" or "/signup" then just continue to the next filter
        if ("/login".equals(reqPath) || "/signup".equals(reqPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            String token = authorizationHeader.substring(7);
            UserDetails utilisateur = this.utilisateurDetailsService.loadUserByUsername(this.jwtService.getSub(token));

            if (this.jwtService.getExpiration(token).after(new Date())){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        utilisateur.getUsername(),
                        utilisateur.getPassword(),
                        utilisateur.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
