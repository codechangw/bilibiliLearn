package com.easypan.service;

import java.io.File;
import java.util.List;

import com.easypan.entity.dto.SessionWebUserDto;
import com.easypan.entity.dto.UploadResultDto;
import com.easypan.entity.query.FileInfoQuery;
import com.easypan.entity.po.FileInfo;
import com.easypan.entity.vo.PaginationResultVO;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件信息 业务接口
 */
public interface FileInfoService {

    /**
     * 根据条件查询列表
     */
    List<FileInfo> findListByParam(FileInfoQuery param);

    /**
     * 根据条件查询列表
     */
    Integer findCountByParam(FileInfoQuery param);

    /**
     * 分页查询
     */
    PaginationResultVO<FileInfo> findListByPage(FileInfoQuery param);

    /**
     * 新增
     */
    Integer add(FileInfo bean);

    /**
     * 批量新增
     */
    Integer addBatch(List<FileInfo> listBean);

    /**
     * 批量新增/修改
     */
    Integer addOrUpdateBatch(List<FileInfo> listBean);

    /**
     * 多条件更新
     */
    Integer updateByParam(FileInfo bean, FileInfoQuery param);

    /**
     * 多条件删除
     */
    Integer deleteByParam(FileInfoQuery param);

    /**
     * 根据FileIdAndUserId查询对象
     */
    FileInfo getFileInfoByFileIdAndUserId(String fileId, String userId);

    /**
     * 根据FileIdAndUserId修改
     */
    Integer updateFileInfoByFileIdAndUserId(FileInfo bean, String fileId, String userId);

    /**
     * 根据FileIdAndUserId删除
     */
    Integer deleteFileInfoByFileIdAndUserId(String fileId, String userId);

    UploadResultDto uploadFile(SessionWebUserDto sessionWebUserDto, String fileId, MultipartFile file, String fileName, String filePid, String fileMd5, Integer chunkIndex, Integer chunks);

    void mergeFiles(String fileId, String userId, File file, String targetFilePath);

}