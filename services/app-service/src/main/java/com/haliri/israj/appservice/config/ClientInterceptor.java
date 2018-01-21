package com.haliri.israj.appservice.config;

import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by israjhaliri on 1/21/18.
 */
@Component
public class ClientInterceptor implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        AppUtils.getLogger(this).info("FILTER ACTIVE");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Set<String> clientListIp = new HashSet<>();
        clientListIp.add("http://localhost:8080");

        String remoteAddress = request.getHeader("Origin");

        if (request != null) {
            clientListIp.forEach(client -> {
                AppUtils.getLogger(this).info("REMOTE ADRESS : {}",remoteAddress);
                if (remoteAddress == null || remoteAddress.isEmpty() || !remoteAddress.equals(client)) {
                    try {
                        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Client Is Not Registered");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        AppUtils.getLogger(this).info("FILTER DESTROY");
    }
}
