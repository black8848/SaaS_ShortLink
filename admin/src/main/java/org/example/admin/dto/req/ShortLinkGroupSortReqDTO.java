package org.example.admin.dto.req;

import lombok.Data;

//短链接分组排序参数
@Data
public class ShortLinkGroupSortReqDTO {

    //分组名
    private String gid;

    //排序
    private Integer sortOrder;
}