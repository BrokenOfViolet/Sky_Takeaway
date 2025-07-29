package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口 - 文件上传到本地
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Value("${sky.upload.dir}")
    private String uploadDir;

    @Value("${sky.upload.base-url}")
    private String baseUrl;


    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}", file);

        try {
            // 原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 生成新文件名
            String newFileName = UUID.randomUUID().toString() + extension;

            // 确保上传目录存在
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 创建目标文件对象
            File destFile = new File(uploadDir + newFileName);
            // 保存文件
            file.transferTo(destFile);

            // 拼接返回路径
            String fileAccessPath = baseUrl + newFileName;
            log.info("文件成功上传至：{}", fileAccessPath);

            return Result.success(fileAccessPath);

        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
    }
}
