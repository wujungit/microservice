package com.kanghe.business.core.manage.feign;

import com.kanghe.business.core.manage.hystrix.MemberInfoHystrixClientFallbackFactory;
import com.kanghe.component.common.base.BaseResult;
import com.kanghe.component.common.dto.member.dto.GetMemberInfoListDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/12 09:27
 * @Description:
 */
@FeignClient(name = "service-member-manage", fallbackFactory = MemberInfoHystrixClientFallbackFactory.class)
@Service
public interface MemberInfoServiceFeign {

    @PostMapping(value = "/memberInfo/getMemberInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult getMemberInfoList(@RequestBody GetMemberInfoListDTO dto);

}
