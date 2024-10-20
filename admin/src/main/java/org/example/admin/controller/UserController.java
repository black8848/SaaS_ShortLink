package org.example.admin.controller;
//用户管理控制层

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.example.admin.common.convention.result.Result;
import org.example.admin.common.convention.result.Results;
import org.example.admin.common.enums.UserErrorCodeEnum;
import org.example.admin.dto.resp.UserRespDTO;
import org.example.admin.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //根据用户名查询用户信息
    @GetMapping("/api/shortlink/v1/user/{username}")
    //GetMapping中的{} <-> PathVariable
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }
}
