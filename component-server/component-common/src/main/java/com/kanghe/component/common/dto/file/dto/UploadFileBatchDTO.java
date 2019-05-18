package com.kanghe.component.common.dto.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/14 18:34
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "UploadFileBatchDTO", description = "UploadFileBatchDTO")
public class UploadFileBatchDTO implements Serializable {

    public List<FileDTO> fileDTOList;

    @NotNull(message = "操作人不能为空")
    @ApiModelProperty(value = "操作人（编码）", name = "operator", required = true)
    private String operator;

    @NotNull(message = "来源不能为空")
    @ApiModelProperty(value = "来源（0-管理端，1-医生端，2-会员端，3-小程序）", name = "origin", required = true)
    private Integer origin;

}
