package com.sugar.ascending.filter;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "logFilter", urlPatterns =  {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class logFilter implements Filter {
    @Autowired
    private Logger logger;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug(">>>>>>>>>>>>Entering LogFilter");

        filterChain.doFilter(servletRequest,servletResponse);
        logger.debug(">>>>>>>>Initializing LogFilter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug(">>>>>>>>>>>>>>Initializing LogFilter");
    }

    @Override
    public void destroy() {
        logger.debug(">>>>>>>>>destroying LogFilter");
    }
}
