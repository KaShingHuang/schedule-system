package service;

import pojo.SysSchedule;
import service.Impl.ScheduleSystemServiceImpl;

import java.util.List;

public interface ScheduleSystemService {

    List<SysSchedule> findItemListByUid(int uid);

   Integer addDefault(int uid);

    Integer updateSchedule(SysSchedule sysSchedule);

    Integer removeSchedule(int sid);
}
