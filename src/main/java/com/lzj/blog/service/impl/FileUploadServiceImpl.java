package com.lzj.blog.service.impl;

import com.lzj.blog.properties.FileProperties;
import com.lzj.blog.service.FileUploadService;
import com.lzj.blog.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${blog.file.url}")
    private String fileUrl;
    @Autowired
    private FileProperties properties;
    @Override
    public String uploadFile(byte[] file, String filePath, String fileName) {
        FileUtils.uploadFile(file,filePath+fileName,properties);
        return fileUrl+filePath+fileName;
    }
}
