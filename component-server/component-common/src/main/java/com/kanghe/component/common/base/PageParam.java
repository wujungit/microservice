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

}
