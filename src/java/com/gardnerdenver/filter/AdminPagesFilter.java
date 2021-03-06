package com.gardnerdenver.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.gardnerdenver.model.FactoryUserItem;

public class AdminPagesFilter extends AbstractFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        FactoryUserItem user = (FactoryUserItem) req.getSession(true).getAttribute("user");

        if (!user.getUserFactory().isFab()) {
            accessDenied(request, response, req);
            return;
        }
        
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
