package test;

import dao.SysScheduleDao;
import dao.impl.SysScheduleDaoImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.SysSchedule;

import java.util.List;

public class TestScheduleDao {
    private static SysScheduleDao scheduleDao;
    @BeforeClass
    public static void InitScheduleImpl(){
        scheduleDao=new SysScheduleDaoImpl();
    }
    @Test
    public void testaddSchedule(){
        int row = scheduleDao.addSchedule(new SysSchedule(null, 2, "学习数据库", 1));
        System.out.println(row);
    }

    @Test
    public void findAll(){
        List<SysSchedule> result = scheduleDao.findAll();
        result.forEach(System.out::println);
    }
}
