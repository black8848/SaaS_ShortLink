package org.example.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class UserDO {

    private long id;

    private String username;

    private String password;

    private String real_name;

    private String phone;

    private String mail;

    private long deletion_time;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    @TableField(fill = FieldFill.INSERT)
    private Integer del_flag;

}
