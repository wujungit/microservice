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
 * @Date: 2019/5/14 18:39
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "DownloadFileBatchDTO", description = "DownloadFileBatchDTO")
public class DownloadFileBatchDTO implements Serializable {

    @NotNull(message = "文件编码列表不能为空")
    @ApiModelProperty(value = "文件编码列表", name = "fileCodes", required = true)
    private List<String> fileCodes;

    @NotNull(message = "下载文件目录不能为空")
    @ApiModelProperty(value = "下载文件目录", name = "directory", required = true)
    private String directory;

}
