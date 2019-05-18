package com.kanghe.service.file.mapper;

import com.kanghe.service.file.entity.FileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FileInfo record);

    int insertBatch(List<FileInfo> list);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    FileInfo selectByCode(String code);

    FileInfo selectByModelId(String modelId);

    FileInfo selectByFileMd5(String fileMd5);

    List<FileInfo> selectByCodes(@Param("codes") List<String> codes);

    List<FileInfo> selectByFileMd5s(@Param("fileMd5s") List<String> fileMd5s);

    int logicDelete(String code);

}