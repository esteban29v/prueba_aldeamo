package com.example.messagepublisher.security;

import com.example.messagepublisher.config.ApiKeyProperties;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ApiKeyFilter implements Filter {

    private static final String HEADER_NAME = "X-API-KEY";

    private final ApiKeyProperties apiKeyProperties;

    public ApiKeyFilter(ApiKeyProperties apiKeyProperties) {
        this.apiKeyProperties = apiKeyProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String apiKey = httpRequest.getHeader(HEADER_NAME);

        if (apiKey == null || !apiKey.equals(apiKeyProperties.getKey())) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: invalid or missing API key");
            return;
        }

        chain.doFilter(request, response);
    }
}