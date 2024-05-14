package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;


public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length-1];
        //使用反射通过方法名来获取下面的方法
        Class aClass = this.getClass();
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //暴力破解方法的访问修饰符的限制
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
