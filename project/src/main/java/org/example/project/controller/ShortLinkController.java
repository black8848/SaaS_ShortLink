package org.example.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.example.project.common.convention.result.Result;
import org.example.project.common.convention.result.Results;
import org.example.project.dto.req.ShortLinkCreateReqDTO;
import org.example.project.dto.req.ShortLinkPageReqDTO;
import org.example.project.dto.resp.ShortLinkCreateRespDTO;
import org.example.project.dto.resp.ShortLinkPageRespDTO;
import org.example.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//短链接控制层
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @PostMapping("/api/short-link/v1/create/")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    //分页查询短链接
    @GetMapping("/api/short-link/v1/page/")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
}
