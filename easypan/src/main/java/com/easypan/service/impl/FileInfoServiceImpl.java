package com.easypan.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.easypan.component.redis.RedisComponent;
import com.easypan.config.AppConfig;
import com.easypan.constants.DateConstants;
import com.easypan.constants.OtherConstants;
import com.easypan.constants.RedisKeyConstants;
import com.easypan.entity.dto.SessionWebUserDto;
import com.easypan.entity.dto.UploadResultDto;
import com.easypan.entity.dto.UserSpaceDto;
import com.easypan.entity.enums.*;
import com.easypan.entity.po.UserInfo;
import com.easypan.entity.query.UserInfoQuery;
import com.easypan.exception.BusinessException;
import com.easypan.service.UserInfoService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.easypan.entity.query.FileInfoQuery;
import com.easypan.entity.po.FileInfo;
import com.easypan.entity.vo.PaginationResultVO;
import com.easypan.entity.query.SimplePage;
import com.easypan.mappers.FileInfoMapper;
import com.easypan.service.FileInfoService;
import com.easypan.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;
import utils.MyUtils;


/**
 * 文件信息 业务接口实现
 */
@Service("fileInfoService")
@Slf4j
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    private FileInfoMapper<FileInfo, FileInfoQuery> fileInfoMapper;
    @Resource
    private RedisComponent redisComponent;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private AppConfig appConfig;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    @Lazy
    private FileInfoService fileInfoService;
    /**
     * 根据条件查询列表
     */
    @Override
    public List<FileInfo> findListByParam(FileInfoQuery param) {
        return this.fileInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(FileInfoQuery param) {
        return this.fileInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<FileInfo> findListByPage(FileInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<FileInfo> list = this.findListByParam(param);
        PaginationResultVO<FileInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(FileInfo bean) {
        return this.fileInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<FileInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.fileInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<FileInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.fileInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(FileInfo bean, FileInfoQuery param) {
        StringTools.checkParam(param);
        return this.fileInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(FileInfoQuery param) {
        StringTools.checkParam(param);
        return this.fileInfoMapper.deleteByParam(param);
    }

    /**
     * 根据FileIdAndUserId获取对象
     */
    @Override
    public FileInfo getFileInfoByFileIdAndUserId(String fileId, String userId) {
        return this.fileInfoMapper.selectByFileIdAndUserId(fileId, userId);
    }

    /**
     * 根据FileIdAndUserId修改
     */
    @Override
    public Integer updateFileInfoByFileIdAndUserId(FileInfo bean, String fileId, String userId) {
        return this.fileInfoMapper.updateByFileIdAndUserId(bean, fileId, userId);
    }

    /**
     * 根据FileIdAndUserId删除
     */
    @Override
    public Integer deleteFileInfoByFileIdAndUserId(String fileId, String userId) {
        return this.fileInfoMapper.deleteByFileIdAndUserId(fileId, userId);
    }

    //Transactional 注解,事务   保证数据一致性

    /**
     * 文件上传
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UploadResultDto uploadFile(SessionWebUserDto sessionWebUserDto, String fileId,
                                      MultipartFile file, String fileName, String filePid,
                                      String fileMd5, Integer chunkIndex, Integer chunks) {
        UploadResultDto uploadResultDto = new UploadResultDto();
        Boolean uploadSuccess = true;
        Boolean uploadFinish = false;
        File tempFolder = null;
        try {
            if (!StringTools.isNotNull(fileId)) {
                fileId = StringTools.getRandomNumber(DateConstants.LENGTH_10);
            }
            uploadResultDto.setFileId(fileId);
            Date date = new Date();
            String userId = sessionWebUserDto.getUserId();
            UserSpaceDto userSpaceDto = userInfoService.getUseSpace(userId);
            //  文件 第一片
            if (chunkIndex == 0) {
                FileInfoQuery query = new FileInfoQuery();
                query.setFileMd5(fileMd5);
                query.setSimplePage(new SimplePage(0, 1));
                //query.setStatus(FileDelFlagEnum.USING.getFlag());
                List<FileInfo> list = this.fileInfoMapper.selectList(query);
                //  秒传
                if (!MyUtils.ListObjectIsEmpty(list)) {
                    FileInfo fileInfo = list.get(0);
                    //  判断使用空间
                    if (fileInfo.getFileSize() + userSpaceDto.getUseSpace() > userSpaceDto.getTotalSpace()) {
                        throw new BusinessException(ResponseCodeEnum.CODE_904);
                    }
                    fileInfo.setFileId(fileId);
                    fileInfo.setFilePid(filePid);
                    fileInfo.setUserId(sessionWebUserDto.getUserId());
                    fileInfo.setCreateTime(date);
                    fileInfo.setLastUpdateTime(date);
                    fileInfo.setStatus(FileStatusEnums.USING.getStatus());
                    fileInfo.setFileMd5(fileMd5);
                    fileInfo.setDelFlag(FileDelFlagEnum.USING.getFlag());
                    //  文件重命名
                    fileName = this.rename(filePid, userId, fileName);
                    fileInfo.setFileName(fileName);
                    fileInfoMapper.insert(fileInfo);
                    //  秒传状态
                    uploadResultDto.setStatus(UploadStatusEnums.UPLOAD_SECONDS.getCode());
                    //  更新用户空间(MySQL && redis)
                    this.updateUserSpace(sessionWebUserDto.getUserId(), userSpaceDto, file.getSize(), null);
                    return uploadResultDto;
                }
            }
            //  判断磁盘空间
            Long currentTempSize = redisComponent.getFileTempSize(userId, fileId);
            //  如果用户空间不足
            if (currentTempSize + file.getSize() + userSpaceDto.getUseSpace() > userSpaceDto.getTotalSpace()) {
                //  销毁临时空间
                redisComponent.delFileTempSize(userId, fileId);
                throw new BusinessException(ResponseCodeEnum.CODE_904);
            }
            //  暂存临时目录
            String tempFolderPath = appConfig.getProjectFolder() + OtherConstants.FILE_FOLDER_TEMP;
            String currentUserFolderPath = sessionWebUserDto.getUserId() + "_" + fileId;
            tempFolder = new File(tempFolderPath + currentUserFolderPath);
            if (!tempFolder.exists()) {
                tempFolder.mkdirs();
            }
            File newFile = new File(tempFolder + "/" + chunkIndex);
            file.transferTo(newFile);
            redisComponent.setFileTempSize(userId, fileId, file.getSize());

            if (chunkIndex < chunks - 1) {
                //  上传中
                uploadResultDto.setStatus(UploadStatusEnums.UPLOADING.getCode());
                //  临时空间使用量增加
                redisComponent.setFileTempSize(userId, fileId, file.getSize());
                return uploadResultDto;
            } else {
                //  上传完成
                uploadResultDto.setStatus(UploadStatusEnums.UPLOAD_FINISH.getCode());
                //  更新用户实际使用空间大小
                String lockKey = StringTools.redisKeyJointH(RedisKeyConstants.REDIS_BLOCK_USER_SPACE, userId);
                RLock rlock = redissonClient.getLock(lockKey);
                boolean isLock = false;
                isLock = rlock.tryLock(3, TimeUnit.SECONDS);
                boolean success = true;
                try {
                    if (isLock) {
                        log.info(lockKey + ":" + "上锁");
                        if (this.checkTempFileSize(userId, fileId)) {
                            this.updateUserSpace(userId, userSpaceDto, currentTempSize + file.getSize(), null);
                        } else {
                            success = false;
                        }
                    } else {
                        log.info(lockKey + ":" + "已被锁");
                    }
                } finally {
                    if (isLock) {
                        log.info("解锁:" + lockKey);
                        rlock.unlock();
                    }
                }
                //  空间不足
                if (!success) {
                    throw new BusinessException(ResponseCodeEnum.CODE_904);
                }
                //  异步合并分片文件
                String suffix = StringTools.getFileSuffix(fileName);
                fileName = this.rename(filePid, userId, fileName);
                String userFolder = appConfig.getProjectFolder() + OtherConstants.FILE_FOLDER_FILE + userId;
                //  文件信息入库
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileId(fileId);
                fileInfo.setUserId(userId);
                fileInfo.setFileMd5(fileMd5);
                fileInfo.setFilePid(filePid);
                fileInfo.setFileName(fileName);
                fileInfo.setFileSize(currentTempSize + file.getSize());
                fileInfo.setCreateTime(date);
                fileInfo.setLastUpdateTime(date);
                fileInfo.setFolderType(FolderTypeEnums.FILE.getType());
                fileInfo.setStatus(FileStatusEnums.TRANSFER.getStatus());
                fileInfo.setFileCategory(FileTypeEnums.getFileTypeBySuffix(suffix).getCategory().getCategory());
                fileInfo.setFileType(FileTypeEnums.getFileTypeBySuffix(suffix).getType());
                //  合并至目标文件路径
                String targetFilePath = userFolder + "/" + fileName;
                fileInfo.setFilePath(targetFilePath);
                fileInfoMapper.insert(fileInfo);
                uploadFinish = true;

                //  事务
                File finalTempFolder = tempFolder;
                TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                    @Override
                    public void afterCommit() {
                        fileInfoService.mergeFiles(fileInfo.getFileId(), userId, finalTempFolder, targetFilePath);
                    }
                });
            }
        } catch (BusinessException e) {
            uploadSuccess = false;
            throw e;
        } catch (Exception e) {
            log.error("文件上传失败");
            uploadSuccess = false;
            e.printStackTrace();
        } finally {
            //  上传失败 || 上传完成
            if ((!uploadSuccess && tempFolder != null) || uploadFinish) {
                try {
                    FileUtils.deleteDirectory(tempFolder);
                } catch (IOException ex) {
                    log.error("删除临时目录失败");
                    ex.printStackTrace();
                }
            }
        }
        return uploadResultDto;
    }

    private String rename(String filePid, String userId, String fileName) {
        FileInfoQuery query = new FileInfoQuery();
        query.setFilePid(filePid);
        query.setFileName(fileName);
        query.setStatus(FileStatusEnums.USING.getStatus());
        query.setDelFlag(FileDelFlagEnum.USING.getFlag());
        Integer c = fileInfoMapper.selectCount(query);
        if (c > 0) {
            return StringTools.rename(fileName);
        } else {
            return fileName;
        }
    }

    private boolean checkTempFileSize(String userId, String fielId) {
        Long fileTempSize = redisComponent.getFileTempSize(userId, fielId);
        UserSpaceDto useSpace = userInfoService.getUseSpace(userId);
        if (fileTempSize + useSpace.getUseSpace() > useSpace.getTotalSpace()) {
            return false;
        }
        return true;
    }

    /**
     * 更新用户空间使用情况
     *
     * @param userId        userid
     * @param userSpaceDto  @Class:UserSpaceDto
     * @param addUseSpace   use space increase
     * @param addTotalSpace total space increase
     */
    private void updateUserSpace(String userId, UserSpaceDto userSpaceDto, Long addUseSpace, Long addTotalSpace) {
        //  update MySQL
        Integer count = userInfoService.updateUserSpace(userId, addUseSpace, addTotalSpace);
        if (count == 0) {
            throw new BusinessException(ResponseCodeEnum.CODE_904);
        }
        //  update redis
        userSpaceDto.setUseSpace(userSpaceDto.getUseSpace() + addUseSpace);
        redisComponent.setUseSpace(userId, userSpaceDto);
    }

    @Override
    @Async
    public void mergeFiles(String fileId, String userId, File file, String targetFilePath) {
        File[] files = file.listFiles();
        FileInfo fileInfo = new FileInfo();
        try {
            MyUtils.mergeFile(files, targetFilePath);
            fileInfo.setStatus(FileStatusEnums.USING.getStatus());
        } catch (IOException e) {
            log.error("合并文件失败");
            fileInfo.setStatus(FileStatusEnums.TRANSFER_FAIL.getStatus());
        } finally {
            fileInfoMapper.updateByFileIdAndUserId(fileInfo, fileId, userId);
        }
    }

}