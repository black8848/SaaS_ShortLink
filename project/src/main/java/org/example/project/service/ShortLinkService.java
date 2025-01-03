package org.example.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.project.dao.entity.ShortLinkDO;
import org.example.project.dto.req.ShortLinkCreateReqDTO;
import org.example.project.dto.req.ShortLinkPageReqDTO;
import org.example.project.dto.resp.ShortLinkCreateRespDTO;
import org.example.project.dto.resp.ShortLinkPageRespDTO;

//短链接接口层
public interface ShortLinkService extends IService<ShortLinkDO> {

    //创建短链接
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    //分页查询短链接
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}
