package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import common.ResultCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.SysUser;
import service.Impl.SysUserServiceImpl;
import service.SysUserService;
import util.MD5Util;
import util.WebUtil;

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
     *  判断用户要注册的名字是否已经占用的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @return 返回已占用或者未占用
     */
    protected void CheckUserNameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //获取当前请求的用户名
        String username = req.getParameter("username");
        //调用服务层的方法，返回用户信息
        SysUser sysuser = userService.findByUsername(username);
        //判断信息是否为空，响应对应的信息
        Result result=Result.ok(null);
        if(sysuser!=null) result=Result.build(null, ResultCodeEnum.USERNAME_USED);
        //将result对象转化为JSON对象传递给客户端,并且告诉客户端响应是一个JSON串
        WebUtil.writeJson(resp,result);

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

    /**
     * 用户登录页面的处理接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @return  返回的是用户是否登录成功，成功跳转login.html,不成功根据原因分别跳转loginUsernameError.html和loginUserPwdError.html
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受客户端提交的参数
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");
        //根据用户名调用Service层获取对应的信息，分情况处理.
        SysUser sysuser=userService.findByUsername(username);
        if(null == sysuser) {
            resp.sendRedirect("/loginUsernameError.html");
            //若用户存在，判断密码是否对应上
        }else if(!MD5Util.encrypt(userPwd).equals((sysuser.getUserPwd()))){
            resp.sendRedirect("/loginUserPwdError.html");
        }
        else {
            //登录成功之后吧用户信息放入session
            HttpSession session = req.getSession();
            session.setAttribute("sysUser", sysuser);
            resp.sendRedirect("/showSchedule.html");
        }
    }
}
