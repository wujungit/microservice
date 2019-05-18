package com.kanghe.component.common.dto.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: W_jun1
 * @Date: 2019/5/15 10:25
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "UploadFileDTO", description = "UploadFileDTO")
public class UploadFileDTO extends FileDTO implements Serializable {

    @NotNull(message = "操作人不能为空")
    @ApiModelProperty(value = "操作人（编码）", name = "operator", required = true)
    private String operator;

    @NotNull(message = "来源不能为空")
    @ApiModelProperty(value = "来源（0-管理端，1-医生端，2-会员端，3-小程序）", name = "origin", required = true)
    private Integer origin;

}
