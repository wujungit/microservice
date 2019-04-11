package com.kanghe.service.member.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanghe.component.common.base.PageResult;
import com.kanghe.component.common.dto.member.dto.GetMemberInfoListDTO;
import com.kanghe.service.member.entity.MemberInfo;
import com.kanghe.service.member.mapper.MemberInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/11 11:07
 * @Description:
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Slf4j
public class MemberInfoService {

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    public PageResult getMemberInfoList(GetMemberInfoListDTO dto) {
        int pageNo = dto.getPageNo();
        int pageSize = dto.getPageSize();
        Page page = PageHelper.startPage(pageNo, pageSize);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberCode(dto.getMemberCode());
        memberInfo.setName(dto.getName());
        List<MemberInfo> list = memberInfoMapper.selectList(memberInfo);
        return new PageResult<>((int) page.getTotal(), list, pageNo, pageSize);
    }

}
