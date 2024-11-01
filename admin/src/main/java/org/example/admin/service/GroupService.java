package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dao.entity.GroupDO;
import org.example.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.example.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

public interface GroupService extends IService<GroupDO> {
    //新增短链接分组
    //groupName：短链接分组名
    void saveGroup(String groupName);

    //查询用户短链接分组集合
    List<ShortLinkGroupRespDTO> listGroup();

    //修改短链接分组信息
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    //删除短链接分组
    void deleteGroup(String gid);
}
