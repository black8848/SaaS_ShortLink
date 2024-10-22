package org.example.admin.controller;
//用户管理控制层

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.example.admin.common.convention.result.Result;
import org.example.admin.common.convention.result.Results;
import org.example.admin.dto.req.UserRegisterReqDTO;
import org.example.admin.dto.req.UserUpdateReqDTO;
import org.example.admin.dto.resp.UserActualRespDTO;
import org.example.admin.dto.resp.UserRespDTO;
import org.example.admin.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //根据用户名查询用户信息
    @GetMapping("/api/short-link/v1/user/{username}")
    //GetMapping中的{} <-> PathVariable
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    //根据用户名查询用户真实信息
    @GetMapping("/api/short-link/v1/actual/user/{username}")
    //GetMapping中的{} <-> PathVariable
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }
    //查询用户名是否可用,存在即已经不可用返回False，不存在即可用返回True
    @GetMapping("/api/short-link/v1/user/available-username")
    public Result<Boolean> availableUsername(@RequestParam("username") String username) {
        return Results.success(userService.availableUsername(username)  );
    }

    //注册用户
    @PostMapping("/api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    @PutMapping("/api/short-link/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }
}
