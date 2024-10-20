package org.example.admin.dao.entity;

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
    private Date create_time;
    private Date update_time;
    private Integer del_time;

}
