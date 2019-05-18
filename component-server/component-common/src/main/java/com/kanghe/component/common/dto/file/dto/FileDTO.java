package com.kanghe.component.common.dto.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: W_jun1
 * @Date: 2019/5/15 14:05
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "FileDTO", description = "FileDTO")
public class FileDTO implements Serializable {

    @NotNull(message = "数据类型不能为空")
    @ApiModelProperty(value = "数据类型（0-bytes，1-base64）", name = "dataType", required = true)
    private Integer dataType;

    @ApiModelProperty(value = "bytes", name = "bytes", required = false)
    private byte[] bytes;

    @ApiModelProperty(value = "base64", name = "base64", required = false)
    private String base64;

    @NotNull(message = "文件真实名称不能为空")
    @ApiModelProperty(value = "文件真实名称", name = "fileRealName", required = true)
    private String fileRealName;

    @NotNull(message = "文件类型不能为空")
    @ApiModelProperty(value = "文件类型（0-文件，1-图像，2-临时文件）", name = "fileType", required = true)
    private Integer fileType;

    @NotNull(message = "文件后缀不能为空")
    @ApiModelProperty(value = "文件后缀", name = "fileSuffix", required = true)
    private String fileSuffix;

    @NotNull(message = "模型ID不能为空")
    @ApiModelProperty(value = "模型ID（用于业务标识）", name = "modelId", required = true)
    private String modelId;

}
