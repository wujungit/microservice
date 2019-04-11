package com.kanghe.component.common.dto.member.dto;

import com.kanghe.component.common.base.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/11 11:28
 * @Description:
 */
@Data
@ApiModel(value = "食材列表查询对象", description = "")
public class GetMemberInfoListDTO extends PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员编码", name = "memberCode", required = false)
    private String memberCode;

    @ApiModelProperty(value = "姓名", name = "name", required = false)
    private String name;

}
