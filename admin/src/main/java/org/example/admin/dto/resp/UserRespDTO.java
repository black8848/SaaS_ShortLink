package org.example.admin.dto.resp;

import lombok.Data;

//用户返回参数响应
@Data
public class UserRespDTO {
    private long id;
    private String username;
    private String real_name;
    private String phone;
    private String mail;
}
