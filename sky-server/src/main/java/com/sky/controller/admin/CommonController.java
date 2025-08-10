package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口 - 支持本地/OSS 双模式文件上传
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Value("${file.storage}")
    private String storageType;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.access-url-prefix}")
    private String accessUrlPrefix;

    @Autowired(required = false) // OSS 模式才注入
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传（本地 / OSS）")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);

        try {
            // 获取文件后缀
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extension;

            String fileUrl;

            if ("oss".equalsIgnoreCase(storageType)) {
                // OSS 上传
                fileUrl = aliOssUtil.upload(file.getBytes(), newFileName);
            } else {
                // 本地上传
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File dest = new File(dir, newFileName);
                file.transferTo(dest);
                fileUrl = accessUrlPrefix + newFileName;
            }

            return Result.success(fileUrl);

        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage(), e);
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
    }
}
