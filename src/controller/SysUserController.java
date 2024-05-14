package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.SysUser;
import service.Impl.SysUserServiceImpl;
import service.SysUserService;

import java.io.IOException;

/**
 * 一般在controller中会有增删查改的请求，
 */
@WebServlet("/user/*")
public class SysUserController extends BaseController{
    private SysUserServiceImpl userService=new SysUserServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Useradd");
    }
    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Userremove");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Userupdate");
    }
    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Userfind");
    }

    /**
     * 接受用户注册请求的业务处理方法(业务接口，不是interface)
     * @param req
     * @param resp
     * @return 返回是否注册成功，如果注册成功的话转跳login页面，否则转跳registFail页面
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受客户端提交的参数
        String UserName = req.getParameter("username");
        String Pwd = req.getParameter("userPwd");
        //调用服务层方法，完成注册功能
        int rows=userService.regist(new SysUser(null,UserName,Pwd));
        //根据注册的结果（成功，失败）进行页面跳转
        if (rows>0){
            resp.sendRedirect("/login.html");
        }
        else resp.sendRedirect("/registFail.html");

    }
}
