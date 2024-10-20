package org.example.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.example.admin.common.serialize.PhoneDesensitizationSerializer;

//用户返回参数响应
@Data
public class UserRespDTO {

    private long id;

    private String username;

    private String real_name;

    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    private String mail;
}
