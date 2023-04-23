package com.example.uet_tty.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Order(1)
public class Filter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object key = session.getAttribute("user_id");
        if(key==null){
            request= new HttpServletRequestWrapper((HttpServletRequest) servletRequest){
                @Override
                public String getRequestURI(){
                    return "/uet_tty/login";
                }
            };
        }
        filterChain.doFilter(request,response);
    }


}
