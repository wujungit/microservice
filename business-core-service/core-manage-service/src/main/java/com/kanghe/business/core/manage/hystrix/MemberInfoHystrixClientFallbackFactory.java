package com.kanghe.business.core.manage.hystrix;

import com.kanghe.business.core.manage.feign.MemberInfoServiceFeign;
import com.kanghe.component.common.base.BaseResult;
import com.kanghe.component.common.dto.member.dto.GetMemberInfoListDTO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/12 09:32
 * @Description:
 */
@Component
public class MemberInfoHystrixClientFallbackFactory implements FallbackFactory<MemberInfoServiceFeign> {
    @Override
    public MemberInfoServiceFeign create(Throwable throwable) {
        return new MemberInfoServiceFeign() {
            @Override
            public BaseResult getMemberInfoList(GetMemberInfoListDTO dto) {
                return new BaseResult<>(false, 200, "操作异常，请核查接口。");
            }
        };
    }
}
