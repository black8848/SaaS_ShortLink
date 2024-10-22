package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dao.entity.UserDO;
import org.example.admin.dto.req.UserLoginReqDTO;
import org.example.admin.dto.req.UserRegisterReqDTO;
import org.example.admin.dto.req.UserUpdateReqDTO;
import org.example.admin.dto.resp.UserLoginRespDTO;
import org.example.admin.dto.resp.UserRespDTO;

//用户接口层
public interface UserService extends IService<UserDO> {

    //根据用户名查询用户信息
    UserRespDTO getUserByUsername(String username);

    //查询用户名称是否可用,存在即已经不可用返回False，不存在即可用返回True
    Boolean availableUsername(String username);

    //注册用户
    void register(UserRegisterReqDTO requestParam);

    //用户信息修改
    void update(UserUpdateReqDTO requestParam);

    //用户登录
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    //检查用户是否登录
    boolean checkLogin(String username, String token);

    //用户退出登录
    void logout(String username, String token);
}
