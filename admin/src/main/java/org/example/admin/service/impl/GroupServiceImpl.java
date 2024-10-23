package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dao.entity.GroupDO;
import org.example.admin.dao.mapper.GroupMapper;
import org.example.admin.service.GroupService;
import org.springframework.stereotype.Service;

//短链接分组接口实现层
@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

}
