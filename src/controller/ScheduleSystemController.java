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

    /**
     * 用户找到日程数据的方法
     * @param req    获得的参数是用户的uid
     * @return   返回根据用户的uid返回的日程数据
     */
    protected void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid= Integer.parseInt(req.getParameter("uid"));   //getParameter返回的是String，要进行转型
        List<SysSchedule> itemList=scheduleService.findItemListByUid(uid);//前端数据类型是一个List的类型，我们这里最终返回的结果数据也是List
        Map<String,Object> data=new HashMap<>();
        data.put("itemList",itemList);
        WebUtil.writeJson(resp, Result.ok(data));
    }

    /**
     * 前端showschedule页面中新增日程按钮对应的方法，可以新增一个默认初始化的日程数据
     * @param req     需要使用的参数是用户的uid
     * @return  不返回任何数据，响应处理成功的状态码
     */
    protected void addDefaultSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        //  调用服务层方法,为当前用户新增一个默认空数据
        scheduleService.addDefault(uid);
        WebUtil.writeJson(resp,Result.ok(null));
    }

    /**    更新对应用户响应的日程数据
     * @param req    需要的参数SysSchedule参数对象的内容
     * @return 不返回任何数据，只返回处理成功的状态码
     */
    protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        // 调用服务层方法,更新数据
        scheduleService.updateSchedule(sysSchedule);
        // 响应成功
        WebUtil.writeJson(resp,Result.ok(null));
    }


    /**
     * 删除指定的日程数据
     * @param req   接收用户要删除的对应的日程数据的sid
     * @return 不返回任何数据，只返回处理成功的状态码
     */
    protected void removeSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要删除的日程id
        int sid = Integer.parseInt(req.getParameter("sid"));
        // 调用服务层方法,删除日程
        scheduleService.removeSchedule(sid);
        // 响应200
        WebUtil.writeJson(resp,Result.ok(null));
    }
}
