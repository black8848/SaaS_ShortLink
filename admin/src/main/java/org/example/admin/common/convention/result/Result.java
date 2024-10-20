package org.example.admin.common.convention.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

//全局返回对象
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5679018624309023727L;
    public static final String SUCCESS_CODE = "0";//正确返回码
    private String code;//返回码
    private String message;//返回消息
    private T data;//响应数据
    private String requestId;//请求ID

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}