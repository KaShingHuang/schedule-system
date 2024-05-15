package dao;

import pojo.SysUser;

public interface SysUserDao {
    /**
     * 向数据库中增加一条用户记录的方法
     * @param sysUser  要增加的记录的Username和userpwd字段以Sysuser对象的形式接受
     * @return 增加成功返回1，增加失败返回0
     */
    int addUser(SysUser sysUser);


    /**
     * 在数据库中查询对应的用户名对应的user信息
     * @param username   要查询的用户名
     * @return   以SysUser对象返回查询到的用户信息，如果查询不到结果为空
     */
    SysUser findByUsername(String username);
}
