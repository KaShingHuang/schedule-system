package dao;

import pojo.SysSchedule;

import java.util.List;
/**
1   dao是数据访问类对象，该类中的对象用于定义对表格的CRUD的方法
2   dao层一般需要定义接口和实现类，接口的目的是可以让开发人员无需关注实现的细节就可以知道这个方法是要来干什么的，实现类可以在dao包下面新建一个impl包
3   写接口的时候要写上代码的文档，首先要写当前类接口是干嘛的，作者，时间
4   每个接口方法，要介绍作用，参数的定义，返回的解释
 */

/**
当前的类是SysUser的接口类，里面包含接口的定义
作者：Kashing
 *
 */
public interface SysScheduleDao {
    /**用于增加一条日程记录
     * @param schedule  日程数据以SysSchedule实体类对象形式入参
     * @return  返回的是影响数据库记录的行数，行数为0说明增加失败，行数大于0说明增加成功
     */
    int addSchedule(SysSchedule schedule);


    /**
     * 查询所有用户的所有日程
     * @return 将所有的日程放入一个list<SysSchedule>集合中返回
     */
    List<SysSchedule> findAll();
}
