package com.kanghe.component.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "PageParam对象", description = "")
public class PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码", name = "pageNo", required = false)
    private int pageNo = 1;

    @ApiModelProperty(value = "页数", name = "pageSize", required = false)
    private int pageSize = 10;

    @ApiModelProperty(value = "排序内容", name = "orderBy", required = false)
    private String orderBy;

    @ApiModelProperty(value = "是否升序排序，默认升序排序", name = "isAsc", required = false)
    private Boolean isAsc = true;

}
