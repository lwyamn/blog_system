package com.lzj.blog.service;

import com.lzj.blog.auth.CurrentUser;

import java.io.FileNotFoundException;

public interface FileUploadService {
    String uploadFile(byte[] file, String filePath, String fileName);
}
