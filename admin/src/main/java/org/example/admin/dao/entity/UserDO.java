package org.example.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//短链接用户实体
@Data
@TableName("t_user")
public class UserDO {

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

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    //删除标识: 0:未删除; 1:已删除
    @TableField(fill = FieldFill.INSERT)
    private Integer del_flag;

}
