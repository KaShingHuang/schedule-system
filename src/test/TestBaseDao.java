package test;

import dao.BaseDao;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.SysUser;

import java.util.List;

public class TestBaseDao {
    private static BaseDao baseDao;
    @BeforeClass
    public static void initBaseDao(){
        baseDao=new BaseDao();
    }

    @Test
    public void testBaseQueryObject(){
        String sql="select count(*) from sys_user";
        Long count = baseDao.baseQueryObject(Long.class, sql);
        System.out.println(count);
    }
    @Test
    public void testbaseQuery(){
        String sql="select uid,username,user_pwd as UserPwd from sys_user";
        List<SysUser> SysUserList = baseDao.baseQuery(SysUser.class, sql);
        SysUserList.forEach(System.out::println);
    }
    @Test
    public void testbaseUpdate(){
        String sql="insert into sys_schedule values(DEFAULT,?,?,?)";
        int row = baseDao.baseUpdate(sql, 1, "学习Java", 0);
        System.out.println(row);
    }
}
