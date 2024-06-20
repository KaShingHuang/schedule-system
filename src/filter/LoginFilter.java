package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.SysUser;

import java.io.IOException;
//@WebFilter(urlPatterns = {"/showSchedule.html","/schedule/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获得session对象
        HttpSession session = request.getSession();
        //从session对象中获得登录的用户对象
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");

        //判断用户对象是否为null
        if (sysUser == null) {
            response.sendRedirect("/login.html");
        }
        else filterChain.doFilter(servletRequest, servletResponse);
            //是的话，返回到login.html
            //否则放行
    }
}
