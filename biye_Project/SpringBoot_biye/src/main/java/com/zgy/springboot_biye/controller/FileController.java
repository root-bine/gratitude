package com.zgy.springboot_biye.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        // 构造文件夹路径
        File folder = new File("E:\\Upload");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 获取文件名和文件路径
        String fileName = file.getOriginalFilename();
        String filePath = "E:\\Upload\\" + fileName;

        try {
            // 判断是否为压缩包，并进行相应的处理
            if (fileName.endsWith(".zip") || fileName.endsWith(".rar")) {
                // 将文件写入磁盘
                file.transferTo(new File(filePath));
                return "上传成功";
            }
            // 普通文件直接写入磁盘
            file.transferTo(new File(filePath));
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }
}


