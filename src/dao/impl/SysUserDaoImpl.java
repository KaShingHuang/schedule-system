package dao.impl;

import dao.BaseDao;
import dao.SysUserDao;
import pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {

    @Override
    public int addUser(SysUser sysUser) {
        String sql="insert into sys_user values(DEFAULT,?,?)";
        return baseUpdate(sql,sysUser.getUsername(),sysUser.getUserPwd());
    }

    @Override
    public SysUser findByUsername(String username) {
        String sql="select uid, username,user_pwd as UserPwd from sys_user where username=?";
        List<SysUser> SysUserList = baseQuery(SysUser.class, sql, username);
        return SysUserList!=null && SysUserList.size()>0 ? SysUserList.get(0) : null;
    }
}