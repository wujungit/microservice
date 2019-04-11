package com.kanghe.service.member.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * t_member_info
 *
 * @author
 */
@Data
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 会员编码
     */
    private String memberCode;

    /**
     * 头像URL
     */
    private String portrait;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0-男，1-女）
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 身高（cm）
     */
    private BigDecimal height;

    /**
     * 体重（kg）
     */
    private BigDecimal weight;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 加密的盐
     */
    private String salt;

    /**
     * 来源（0-管理端，1-会员端，2-医生端）
     */
    private Integer origin;

    /**
     * 状态（0-正常，1禁用）
     */
    private Integer status;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 是否已完善个人资料（0-否，1-是）
     */
    private Integer completed;

    /**
     * 是否已绑定微信（0-否，1-是）
     */
    private Integer ifBindWechat;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识（0-正常，1-删除）
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remarks;

}