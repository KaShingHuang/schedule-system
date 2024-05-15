package service;

import pojo.SysUser;

/**
 * 同样的service包中，对于每一个数据库表格都要创建对应的一个Service接口类和实现类
 */
public interface SysUserService {
    public int regist(SysUser sysUser);


    /**
     * 根据用户名返回对应的用户信息。
     * @param username   用户名
     * @return  查询得到的SysUser对象，如果用户名不存在，则返回null
     */
    SysUser findByUsername(String username);
}
