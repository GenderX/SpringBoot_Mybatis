package com.wms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author ：LiCan
 * @date ：Created in 2019/1/14 13:55
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController

public class UploadController {
    @Value("${upload.filePath}")
    String filePath ;

    /**
     * 文件长传类
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
        }
        return "上传失败！";
    }

    }




