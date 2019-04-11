package com.kanghe.service.member.mapper;

import com.kanghe.service.member.entity.MemberInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberInfo record);

    int insertSelective(MemberInfo record);

    MemberInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);

    List<MemberInfo> selectList(MemberInfo record);
}