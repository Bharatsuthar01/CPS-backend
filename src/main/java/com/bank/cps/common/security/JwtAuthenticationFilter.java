package com.bank.cps.common.security;

import com.bank.cps.common.constants.SecurityConstants;
import com.bank.cps.user.document.UserDocument;
import com.bank.cps.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenBlacklistService tokenBlacklistService;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository,
            TokenBlacklistService tokenBlacklistService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(SecurityConstants.AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
        if (tokenBlacklistService.isBlacklisted(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Claims claims = jwtService.parse(token);
            String username = claims.getSubject();
            UserDocument user = userRepository.findByUsername(username).orElse(null);
            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (String role : user.getRoles())
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                for (String permission : user.getPermissions())
                    authorities.add(new SimpleGrantedAuthority(permission));
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                        authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception ignored) {
        }
        filterChain.doFilter(request, response);
    }
}
