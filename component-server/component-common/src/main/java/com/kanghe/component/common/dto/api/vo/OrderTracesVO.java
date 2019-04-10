package com.kanghe.component.common.dto.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/9 16:52
 * @Description:
 */
@Data
public class OrderTracesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String eBusinessID;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 快递公司编码
     */
    private String shipperCode;

    /**
     * 快递公司名称
     */
    private String shipperName;

    /**
     * 物流运单号
     */
    private String logisticCode;

    /**
     * 成功与否
     */
    private Boolean success;

    /**
     * 失败原因
     */
    private String reason;

    /**
     * 物流状态：2-在途中,3-签收,4-问题件
     */
    private String state;

    private List<TraceVO> traces;

}
