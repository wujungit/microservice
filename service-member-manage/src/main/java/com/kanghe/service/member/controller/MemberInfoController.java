package com.kanghe.service.member.controller;

import com.alibaba.fastjson.JSON;
import com.kanghe.component.common.base.BaseResult;
import com.kanghe.component.common.base.PageResult;
import com.kanghe.component.common.constant.ResponseCode;
import com.kanghe.component.common.dto.member.dto.GetMemberInfoListDTO;
import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.service.member.service.MemberInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/11 11:06
 * @Description:
 */
@Api(value = "memberInfoApi", description = "会员相关的接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(value = "/memberInfo")
@Slf4j
public class MemberInfoController {

    @Autowired
    private MemberInfoService memberInfoService;

    @ApiOperation(value = "会员信息列表查询", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/getMemberInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult getMemberInfoList(@ApiParam @RequestBody GetMemberInfoListDTO dto) {
        log.info("getMemberInfoList req: {}", JSON.toJSONString(dto));
        PageResult result = memberInfoService.getMemberInfoList(dto);
        return new BaseResult<>(ResponseCode.SUCCESS, result, ResultEnum.SUCCESS.getCode(), "会员信息列表查询成功");
    }

}
