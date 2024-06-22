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

    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        String sql="select sid,uid,title,completed from sys_schedule where uid=?";
        return baseQuery(SysSchedule.class,sql,uid);
    }
    public Integer addDefault(int uid) {
        String sql = "insert into sys_schedule value(default,?,'请输入日程',0)";
        return baseUpdate(sql,uid);
    }

    public Integer updateSchedule(SysSchedule sysSchedule) {
        String sql ="update sys_schedule set title = ? ,completed =  ? where sid =?";
        return baseUpdate(sql,sysSchedule.getTitle(),sysSchedule.getCompleted(),sysSchedule.getSid());
    }

    @Override
    public Integer removeBySid(int sid) {
        String sql ="delete from sys_schedule where sid = ?";
        return baseUpdate(sql,sid);
    }

}
