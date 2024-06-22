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
import java.util.HashMap;
import java.util.Map;

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
        //封装结果对象
        Result result=null;
        if(null ==sysuser){
            // 未占用,创建一个code为200的对象
            result= Result.ok(null);
        }else{
            // 占用, 创建一个结果为505的对象
            result= Result.build(null, ResultCodeEnum.USERNAME_USED);

        }
        // 将result对象转换成JSON并响应给客户端
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
        SysUser registUser = WebUtil.readJson(req, SysUser.class);
        //调用服务层方法，完成注册功能
        int rows=userService.regist(registUser);
        Result result =null;
        //根据注册的结果（成功，失败）进行页面跳转
        if(rows>0){
            result=Result.ok(null);
        }else{
            result =Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);

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
        SysUser inputUser = WebUtil.readJson(req, SysUser.class);
        // 调用服务层方法,根据用户名查询数据库中是否有一个用户
        SysUser loginUser =userService.findByUsername(inputUser.getUsername());

        Result result = null;

        if(null == loginUser){
            // 没有根据用户名找到用户,说明用户名有误
            result=Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }else if(! loginUser.getUserPwd().equals(MD5Util.encrypt(inputUser.getUserPwd()))){
            // 用户密码有误,
            result=Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }else{
            // 登录成功,信息存入session
            req.getSession().setAttribute("sysUser",loginUser);
            // 登录成功
            // 将密码请空后,将用户信息响应给客户端
            loginUser.setUserPwd("");
            Map<String, Object> data = new HashMap<>();
            data.put("loginUser",loginUser);
            result=Result.ok(data);
        }

        WebUtil.writeJson(resp,result);
    }



}
