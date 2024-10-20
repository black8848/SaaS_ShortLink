package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dao.entity.UserDO;
import org.example.admin.dto.resp.UserRespDTO;

//用户接口层
public interface UserService extends IService<UserDO> {
    UserRespDTO getUserByUsername(String username);
}
