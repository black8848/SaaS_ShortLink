package org.example.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.project.common.convention.exception.ServiceException;
import org.example.project.dao.entity.ShortLinkDO;
import org.example.project.dao.mapper.ShortLinkMapper;
import org.example.project.dto.req.ShortLinkCreateReqDTO;
import org.example.project.dto.req.ShortLinkPageReqDTO;
import org.example.project.dto.resp.ShortLinkCreateRespDTO;
import org.example.project.dto.resp.ShortLinkPageRespDTO;
import org.example.project.service.ShortLinkService;
import org.example.project.toolkit.HashUtil;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

//短链接接口实现层
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        String fullShortUrl = StrBuilder.create(requestParam.getDomain())
                .append('/')
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .createdType(requestParam.getCreatedType())
                .describe(requestParam.getDescribe())
                .domain(requestParam.getDomain())
                .gid(requestParam.getGid())
                .originUrl(requestParam.getOriginUrl())
                .validDate(requestParam.getValidDate())
                .validDateType(requestParam.getValidDateType())
                .enableStatus(0)
                .fullShortUrl(fullShortUrl)
                .shortUri(shortLinkSuffix)
                .build();
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException ex) {
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            if (hasShortLinkDO != null) {
                log.warn("短链接：{} 重复入库", fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        shortUriCreateCachePenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateRespDTO.builder()
                .gid(requestParam.getGid())
                .originUrl(requestParam.getOriginUrl())
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .build();
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0)
                .eq(ShortLinkDO::getDel_flag, 0)
                .orderByDesc(ShortLinkDO::getCreate_time);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        String shortUri;
        int customGenerateCount = 0;
        while (true) {
            if (customGenerateCount > 10) {
                throw new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originUrl = requestParam.getOriginUrl();
            // 添加时间戳，用于尝试生成不同的短链接
            originUrl += System.currentTimeMillis();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(requestParam.getDomain() + '/' + shortUri)) {
                break;
            }
            customGenerateCount++;
        }
        return shortUri;
    }
}
