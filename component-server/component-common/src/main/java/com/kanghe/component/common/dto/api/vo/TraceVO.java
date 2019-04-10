package com.kanghe.component.common.dto.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/9 16:56
 * @Description:
 */
@Data
public class TraceVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acceptTime;

    /**
     * 描述
     */
    private String acceptStation;

    /**
     * 备注
     */
    private String remark;

}
