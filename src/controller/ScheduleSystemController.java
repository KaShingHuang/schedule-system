package controller;

import common.Result;
import dao.BaseDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.SysSchedule;
import service.Impl.ScheduleSystemServiceImpl;
import service.ScheduleSystemService;
import util.WebUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一般在controller中会有增删查改的请求，处理这些请求的方法是在请求的路径后增加一层路径比如；
 * 增加日程的时候 /schedule/add
 * 删除日程的时候 /schedule/remove
 * 修改日程的时候 /schedule/update
 * 查询日程的时候 /schedule/find
 */
@WebServlet("/schedule/*")
public class ScheduleSystemController extends BaseController {
    private ScheduleSystemService scheduleService=new ScheduleSystemServiceImpl();
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


    protected void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid= Integer.parseInt(req.getParameter("uid"));
        List<SysSchedule> itemList=scheduleService.findItemListByUid(uid);
        Map<String,Object> data=new HashMap<>();
        data.put("itemList",itemList);
        WebUtil.writeJson(resp, Result.ok(data));
    }

    protected void addDefaultSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        //  调用服务层方法,为当前用户新增一个默认空数据
        scheduleService.addDefault(uid);

        WebUtil.writeJson(resp,Result.ok(null));
    }


    protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        // 调用服务层方法,更新数据
        scheduleService.updateSchedule(sysSchedule);

        // 响应成功
        WebUtil.writeJson(resp,Result.ok(null));
    }

    protected void removeSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要删除的日程id
        int sid = Integer.parseInt(req.getParameter("sid"));
        // 调用服务层方法,删除日程
        scheduleService.removeSchedule(sid);
        // 响应200
        WebUtil.writeJson(resp,Result.ok(null));
    }
}
