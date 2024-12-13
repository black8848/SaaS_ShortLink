package org.example.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.project.dao.entity.ShortLinkDO;
import org.example.project.dao.mapper.ShortLinkMapper;
import org.example.project.dto.req.ShortLinkCreateReqDTO;
import org.example.project.dto.resp.ShortLinkCreateRespDTO;
import org.example.project.service.ShortLinkService;
import org.example.project.toolkit.HashUtil;
import org.springframework.stereotype.Service;

//短链接接口实现层
@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setFullShortUrl(requestParam.getDomain() + '/' + shortLinkSuffix);
        shortLinkDO.setEnableStatus(1);
        baseMapper.insert(shortLinkDO);
        return ShortLinkCreateRespDTO.builder()
                .gid(requestParam.getGid())
                .originUrl(requestParam.getOriginUrl())
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam){
        String originUrl = requestParam.getOriginUrl();
        return HashUtil.hashToBase62(originUrl);
    }
}
