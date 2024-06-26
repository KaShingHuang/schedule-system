package service;

import pojo.SysSchedule;
import service.Impl.ScheduleSystemServiceImpl;

import java.util.List;

public interface ScheduleSystemService {

    /**
     * 通过用户的uid找到该用户的日程列表
     * @param uid     用户的uid
     * @return    该用户的日程列表
     */
    List<SysSchedule> findItemListByUid(int uid);

    /**
     * 通过用户的uid添加一条默认初始化的日程数据
     * @param uid
     * @return    是否添加成功
     */
   Integer addDefault(int uid);

    /**    通过sysSchedule对象更新日程数据内容
     *
     * @param sysSchedule
     * @return      是否更新成功
     */
    Integer updateSchedule(SysSchedule sysSchedule);

    /**
     * 根据日程数据的sid删除掉对应的日程诗句
     * @param sid
     * @return   是否删除成功
     */
    Integer removeSchedule(int sid);
}
