package controller;

import dao.BaseDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 一般在controller中会有增删查改的请求，处理这些请求的方法是在请求的路径后增加一层路径比如；
 * 增加日程的时候 /schedule/add
 * 删除日程的时候 /schedule/remove
 * 修改日程的时候 /schedule/update
 * 查询日程的时候 /schedule/find
 */
@WebServlet("/schedule/*")
public class ScheduleSystemController extends BaseController {
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add");
    }
    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("remove");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update");
    }
    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("find");
    }
}
