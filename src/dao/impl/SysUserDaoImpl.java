package dao.impl;

import dao.BaseDao;
import dao.SysUserDao;
import pojo.SysUser;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {

    @Override
    public int addUser(SysUser sysUser) {
        String sql="insert into sys_user values(DEFAULT,?,?)";
        return baseUpdate(sql,sysUser.getUsername(),sysUser.getUserPwd());
    }
}
