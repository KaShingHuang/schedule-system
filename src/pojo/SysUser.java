package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data  //这个对应了重写hashcode euqals 和tostring，Getter和Setter方法
public class SysUser {
    private Integer uid;
    private String username;
    private String UserPwd;
}
