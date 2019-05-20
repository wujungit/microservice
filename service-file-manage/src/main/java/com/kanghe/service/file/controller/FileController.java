//package com.kanghe.service.file.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.kanghe.component.common.base.BaseResult;
//import com.kanghe.component.common.dto.file.dto.DownloadFileBatchDTO;
//import com.kanghe.component.common.dto.file.dto.UploadFileBatchDTO;
//import com.kanghe.component.common.dto.file.dto.UploadFileDTO;
//import com.kanghe.component.common.dto.file.vo.FileInfoVO;
//import com.kanghe.service.file.service.IFileService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
///**
// * @Author: W_jun1
// * @Date: 2019/5/14 18:09
// * @Description: 文件操作相关的接口
// */
//@Api(value = "fileApi", description = "文件操作相关的接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@RestController
//@RequestMapping(value = "/file")
//@Slf4j
//public class FileController {
//
//    @Autowired
//    private IFileService fileService;
//
//    @ApiOperation(value = "通过模型ID获取文件信息", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping(value = "/getFileInfoByModelId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public BaseResult<?> getFileInfoByModelId(@ApiParam(value = "模型ID", required = true) @RequestParam("modelId") String modelId) {
//        log.info("getFileInfoByModelId req: modelId={}", modelId);
//        FileInfoVO result = fileService.getFileInfoByModelId(modelId);
//        return getBaseResultSuccess(result, "上传文件成功");
//    }
//
//    /**
//     * 上传文件
//     *
//     * @param dto
//     * @return
//     */
//    @ApiOperation(value = "上传文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public BaseResult<?> uploadFile(@ApiParam @RequestBody @Valid UploadFileDTO dto) {
////        log.info("uploadFile req: {}", JSON.toJSONString(dto));
//        FileInfoVO result = fileService.uploadFile(dto);
//        return getBaseResultSuccess(result, "上传文件成功");
//    }
//
//    /**
//     * 批量上传文件
//     *
//     * @param dto
//     * @return
//     */
//    @ApiOperation(value = "批量上传文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @PostMapping(value = "/uploadBatch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public BaseResult<?> uploadFileBatch(@ApiParam @RequestBody @Valid UploadFileBatchDTO dto) {
////        log.info("uploadFileBatch req: {}", JSON.toJSONString(dto));
//        List<FileInfoVO> result = fileService.uploadFileBatch(dto);
//        return getBaseResultSuccess(result, "批量上传文件成功");
//    }
//
//    /**
//     * 下载文件
//     *
//     * @param fileCode
//     * @param directory
//     * @return
//     */
//    @ApiOperation(value = "下载文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping(value = "/download", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public BaseResult<?> downloadFile(@ApiParam(value = "文件编码", required = true) @RequestParam("fileCode") String fileCode,
//                                      @ApiParam(value = "下载文件目录", required = true) @RequestParam("directory") String directory) {
//        log.info("downloadFile req: fileCode={},directory={}", fileCode, directory);
//        FileInfoVO result = fileService.downloadFile(fileCode, directory);
//        return getBaseResultSuccess(result, "下载文件成功");
//    }
//
//    /**
//     * 批量下载文件
//     *
//     * @param dto
//     * @return
//     */
//    @ApiOperation(value = "批量下载文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @PostMapping(value = "/downloadBatch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public BaseResult<?> downloadFileBatch(@ApiParam @RequestBody DownloadFileBatchDTO dto) {
//        log.info("downloadFileBatch req: {}", JSON.toJSONString(dto));
//        List<String> fileCodes = dto.getFileCodes();
//        String directory = dto.getDirectory();
//        List<FileInfoVO> result = fileService.downloadFileBatch(fileCodes, directory);
//        return getBaseResultSuccess(result, "批量下载文件成功");
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param fileCode
//     * @return
//     */
//    @ApiOperation(value = "删除文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @GetMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public BaseResult<?> deleteFile(@ApiParam(value = "文件编码", required = true) @RequestParam("fileCode") String fileCode) {
//        log.info("deleteFile req: fileCode={}", fileCode);
//        Boolean result = fileService.deleteFile(fileCode);
//        return getBaseResultSuccess(result, "删除文件成功");
//    }
//
//    /**
//     * 批量删除文件
//     *
//     * @param fileCodes
//     * @return
//     */
//    @ApiOperation(value = "批量删除文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @PostMapping(value = "/deleteBatch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public BaseResult<?> deleteFileBatch(@ApiParam @RequestBody List<String> fileCodes) {
//        log.info("deleteFileBatch req: {}", JSON.toJSONString(fileCodes));
//        Boolean result = fileService.deleteFileBatch(fileCodes);
//        return getBaseResultSuccess(result, "批量删除文件成功");
//    }
//
//    private BaseResult<?> getBaseResultSuccess(Object res, String msg) {
//        return BaseResult.buildSuccess(msg, res);
//    }
//
//    private BaseResult<?> getBaseResultFailure(Object res, String msg) {
//        return BaseResult.buildFailure(msg, res);
//    }
//
//}
