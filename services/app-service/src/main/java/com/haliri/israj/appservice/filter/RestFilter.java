package com.haliri.israj.appservice.filter;


import com.haliri.israj.appservice.handler.CustomRequestHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by israjhaliri on 8/31/17.
 */
public class RestFilter implements Filter {
    
    @Override
    public void init(FilterConfig fc) throws ServletException { }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        CustomRequestHandler requestHandler = new CustomRequestHandler((HttpServletRequest) sr);
        fc.doFilter(requestHandler, sr1);
    }

    @Override
    public void destroy() {
    }

}
