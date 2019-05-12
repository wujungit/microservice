package com.kanghe.component.file.mapper;

import com.kanghe.component.file.entity.FileOperateRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface FileOperateRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileOperateRecord record);

    int insertSelective(FileOperateRecord record);

    FileOperateRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileOperateRecord record);

    int updateByPrimaryKey(FileOperateRecord record);
}