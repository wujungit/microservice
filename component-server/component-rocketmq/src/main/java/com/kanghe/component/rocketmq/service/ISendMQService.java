package com.kanghe.component.rocketmq.service;

/**
 * @Author: W_jun1
 * @Date: 2019/4/23 16:41
 * @Description:
 */
public interface ISendMQService {

    /**
     * 发送MQ消息
     *
     * @param topic 主题
     * @param tag   标签
     * @param body  内容
     * @return 响应结果（SUCCEED/ERROR）
     */
    public String send(String topic, String tag, String body);

}
