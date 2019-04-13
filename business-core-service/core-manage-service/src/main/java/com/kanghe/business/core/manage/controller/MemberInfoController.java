package com.kanghe.business.core.manage.controller;

import com.kanghe.business.core.manage.feign.MemberInfoServiceFeign;
import com.kanghe.component.common.base.BaseResult;
import com.kanghe.component.common.dto.member.dto.GetMemberInfoListDTO;
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
    private MemberInfoServiceFeign memberInfoServiceFeign;

    @ApiOperation(value = "会员信息列表查询", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/getMemberInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult getMemberInfoList(@ApiParam @RequestBody GetMemberInfoListDTO dto) {
        return memberInfoServiceFeign.getMemberInfoList(dto);
    }

}
