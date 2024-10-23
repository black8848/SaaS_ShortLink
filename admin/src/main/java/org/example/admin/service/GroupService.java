package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dao.entity.GroupDO;

public interface GroupService extends IService<GroupDO> {
    //新增短链接分组
    //groupName：短链接分组名
    void saveGroup(String groupName);
}
