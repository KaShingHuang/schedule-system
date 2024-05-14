package dao;

import pojo.SysUser;

public interface SysUserDao {
    /**
     * 向数据库中增加一条用户记录的方法
     * @param sysUser  要增加的记录的Username和userpwd字段以Sysuser对象的形式接受
     * @return 增加成功返回1，增加失败返回0
     */
    int addUser(SysUser sysUser);
}
