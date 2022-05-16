package com.qwqaq.costwarden.filter;

import com.qwqaq.costwarden.controller.BaseController;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PermissionFilter")
public class PermissionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!BaseController.LoginRequired((HttpServletRequest) request, (HttpServletResponse) response)) {
            return;
        }

        chain.doFilter(request, response);
    }
}
