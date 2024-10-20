package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dao.entity.UserDO;
import org.example.admin.dto.resp.UserRespDTO;

//用户接口层
public interface UserService extends IService<UserDO> {

    //根据用户名查询用户信息
    UserRespDTO getUserByUsername(String username);

    //查询用户名称是否存在,存在返回True，不存在返回False
    Boolean hasUsername(String username);
}
