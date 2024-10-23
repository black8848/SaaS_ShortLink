package org.example.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.admin.common.database.BaseDO;

//短链接用户实体
@Data
@TableName("t_user")
public class UserDO extends BaseDO {

    //id
    private long id;

    //用户名
    private String username;

    //密码
    private String password;

    //真实姓名
    private String real_name;

    //手机号
    private String phone;

    //邮箱
    private String mail;

    //删除时间
    private long deletion_time;

}
