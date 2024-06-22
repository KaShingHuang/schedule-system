package service.Impl;

import dao.SysScheduleDao;
import dao.impl.SysScheduleDaoImpl;
import pojo.SysSchedule;
import service.ScheduleSystemService;

import java.util.List;

public class ScheduleSystemServiceImpl implements ScheduleSystemService {
    private SysScheduleDao scheduleDao=new SysScheduleDaoImpl();

    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        return scheduleDao.findItemListByUid(uid);
    }

    @Override
    public Integer addDefault(int uid) {
        return scheduleDao.addDefault(uid);
    }

    @Override
    public Integer updateSchedule(SysSchedule sysSchedule) {
        return scheduleDao.updateSchedule(sysSchedule);
    }

    @Override
    public Integer removeSchedule(int sid) {
        return scheduleDao.removeBySid(sid);
    }
}
