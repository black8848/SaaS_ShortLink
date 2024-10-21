package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dao.entity.UserDO;
import org.example.admin.dto.req.UserRegisterReqDTO;
import org.example.admin.dto.resp.UserRespDTO;

//用户接口层
public interface UserService extends IService<UserDO> {

    //根据用户名查询用户信息
    UserRespDTO getUserByUsername(String username);

    //查询用户名称是否可用,存在即已经不可用返回False，不存在即可用返回True
    Boolean availableUsername(String username);

    //注册用户
    void register(UserRegisterReqDTO requestParam);
}
