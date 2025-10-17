package com.zredtea.TeaWIKI.util;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.util.IdUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Component
public class FileUploadUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

    @Value("${file.upload.path}")
    private static String uploadPath;

    /**
     * 上传头像文件
     */
    public static String uploadAvatar(MultipartFile file) throws IOException {
        // 验证文件
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        }

        // 验证文件类型
        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        if (!Arrays.asList("jpg", "jpeg", "png").contains(fileExtension.toLowerCase())) {
            throw new RuntimeException("不支持的文件格式");
        }

        // 生成唯一文件名
        String fileName = generateFileName(originalFilename);

        // 创建目录
        File dest = new File(uploadPath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        // 保存文件
        file.transferTo(dest);

        return fileName;
    }

    /**
     * 获取文件扩展名
     */
    private static String getFileExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        }
        return "";
    }

    /**
     * 生成唯一文件名
     * @param originalFilename 源文件名
     * @return
     */
    private static String generateFileName(String originalFilename) {
        String fileExtension = getFileExtension(originalFilename);
        return IdUtil.simpleUUID() + "." + fileExtension;
    }
}
