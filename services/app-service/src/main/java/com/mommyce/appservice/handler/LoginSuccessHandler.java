package com.mommyce.appservice.handler;

import com.mommyce.appservice.config.UserDetailsConfig;
import com.mommyce.appservice.utils.JwtTokenUtil;
import me.haliri.israj.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by israjhaliri on 8/30/17.
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsConfig userDetailsConfig;

    @Value("${jwt.header}")
    private String tokenHeader;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
        AppUtils.getLogger(this).debug("LOGIN SUCCESS");
        handle(request, response, a);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();
        final String token = jwtTokenUtil.generateToken(username);

        response.setHeader(tokenHeader, token);
        response.setHeader(HttpHeaders.LOCATION, request.getServletContext().getContextPath() + "/#/");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"code\":\""+response.getStatus()
                    + "\", \"status\":\"SUCESS\", "
                    + "\"token\":\""+token+"\"}");
            writer.flush();
            writer.close();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
