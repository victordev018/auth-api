package com.victordev.auth_api.infra.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victordev.auth_api.exception.StandardError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        int status = HttpServletResponse.SC_FORBIDDEN;      // 403
        response.setContentType("application/json");
        response.setStatus(status);
        StandardError standardError = new StandardError(status, "Authorization Error.", "You do not have sufficient permissions.");

        // object to json and String
        ObjectMapper objectMapper = new ObjectMapper();
        String responseJSON = objectMapper.writeValueAsString(standardError);
        response.getWriter().write(responseJSON);
    }
}
