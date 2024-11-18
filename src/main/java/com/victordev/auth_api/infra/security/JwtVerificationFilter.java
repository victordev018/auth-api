package com.victordev.auth_api.infra.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victordev.auth_api.exception.StandardError;
import com.victordev.auth_api.infra.exception.TokenNotProviderException;
import com.victordev.auth_api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if(pathIsPublic(path)){
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = this.recoverToken(request);
            tokenService.validateToken(token);
            filterChain.doFilter(request, response);
        }
        catch (JWTDecodeException exception) {
            handleException(response, "Invalid JWT format");
        }
        catch (TokenExpiredException exception) {
            handleException(response,"JWT token has expired");
        }
        catch (TokenNotProviderException exception) {
            handleException(response, exception.getMessage());
        }
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) throw new TokenNotProviderException("Token is not provider.");
        return authHeader.replace("Bearer ", "");
    }

    private Boolean pathIsPublic(String path) {
        List<String> paths = List.of(
                "/auth/login", "/auth/register"
        );
        return paths.contains(path);
    }

    private void handleException(HttpServletResponse response, String message) throws IOException {
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        response.setContentType("application/json");
        response.setStatus(status);
        StandardError standardError = new StandardError(status, "Jwt Verification Error.", message);

        // object to json and String
        ObjectMapper objectMapper = new ObjectMapper();
        String responseJSON = objectMapper.writeValueAsString(standardError);
        response.getWriter().write(responseJSON);
    }
}
