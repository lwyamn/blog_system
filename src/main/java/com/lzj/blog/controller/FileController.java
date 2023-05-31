package com.lzj.blog.controller;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@Tag(name = "文件接口")
public class FileController {
    @Autowired
    private FileUploadService fileUploadService;

    @Value("${blog.file.upload.path}")
    private String uploadPath;

    @Operation(summary = "上传文件")
    @PostMapping(BlogUrl.File.UPLOAD_FILE)
    public Result<String> upload(@RequestParam("file") MultipartFile file, CurrentUser user) throws Exception {
        String fileSource;
        fileSource = fileUploadService.uploadFile(file.getBytes(), String.format(uploadPath,user.getId()), file.getOriginalFilename());
        return Result.success("上传文件成功",fileSource);
    }

}
