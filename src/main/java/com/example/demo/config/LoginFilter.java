package com.example.demo.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author
 * @date 2020/9/21
 **/

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    String NO_LOGIN = "无权限请先登录";


    /**
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        //是否需要过滤
        boolean needFilter = true;
        //不需要登录就可以访问的路径(比如:注册登录等)
        List<String> list = new ArrayList<String>(Arrays.asList("/user/login", "/user/submit", "/", "/login", "/index", "/user/index","/user/stafflogin","/stafflogin"));
        if (list.contains(uri) || uri.contains(".css") || uri.contains(".js")) {    //不需要过滤的
            chain.doFilter(servletRequest, servletResponse);
        } else {
            // session中包含User对象,则是登录状态
            if (session != null && session.getAttribute("user") != null ) {
                chain.doFilter(request, response);
            }
            /*// session中包含staff对象,则是登录状态
            if (session != null && session.getAttribute("staff") != null){
                chain.doFilter(request, response);
            }*/else {
                response.sendRedirect("/login");

                /*request.getRequestDispatcher("/user/login").forward(request, response);*/   //按道理也可以执行
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
