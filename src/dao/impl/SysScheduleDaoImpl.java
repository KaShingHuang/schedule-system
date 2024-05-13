package dao.impl;

import dao.BaseDao;
import dao.SysScheduleDao;
import pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {
    @Override
    public int addSchedule(SysSchedule schedule){
        String sql="insert into sys_schedule values(DEFAULT,?,?,?)";
        int row = baseUpdate(sql, schedule.getUid(), schedule.getTitle(), schedule.getCompleted());
        return row;
    }

    @Override
    public List<SysSchedule> findAll(){
        String sql="select sid,uid,title,completed from sys_schedule";
        List<SysSchedule> scheduleList = baseQuery(SysSchedule.class, sql);
        return scheduleList;
    }
}
