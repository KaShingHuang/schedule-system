package service.Impl;

import dao.SysUserDao;
import dao.impl.SysUserDaoImpl;
import pojo.SysUser;
import service.SysUserService;
import util.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao userDao=new SysUserDaoImpl();

    /**
     * 实现注册功能调用Dao层进行服务
     * @param sysUser  要注册的用户名和明文密码以SysUser对象的形式存放
     * @return 注册成功返回1，注册失败返回0
     */
    public int regist(SysUser sysUser) {
        //因为数据库中存储的是密文密码，所以这里要将明文密码转化为密文密码存储
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        //调用Dao层的方法，将Sysuser信息存入数据库
        return userDao.addUser(sysUser);

    }

    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
