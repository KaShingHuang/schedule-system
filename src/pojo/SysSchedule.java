package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
每个实体类需要满足的标准：
1   实体类的类名要和表格的名称对应（不要求一样）
2   实体类的属性名和表格的列名应该对应
3   每个属性都必须是私有的
4   每个属性都必须具备getter 和setter
5   必须具备无参构造方法和全参构造方法
6   应该实现序列化接口(缓存，分布式项目数据传递 可能会将对象序列化)
7   应该重写类的hashcode equals 和tostring方法

实现全参和无参构造方法和重写上述要求的方法的时候可以采用lombok，在编译的时候idea会自动进行添加，步骤：
1   idea安装lombok插件
2   检查是否勾选了设置中的Compiler的Annotation processors中的enable anootation processing是否勾选上(必须要检查！！！！)
3   在WEB-INF中新建一个lib文件夹导入lombok的jar包
4   采用下面的注解方式进行使用
*/
@AllArgsConstructor
@NoArgsConstructor
@Data  //这个对应了重写hashcode euqals 和tostring方法
public class SysSchedule {
    private Integer sid;
    private Integer uid;
    private String title;
    private Integer completed;

}
