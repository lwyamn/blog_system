package com.lzj.blog.utils;

import com.google.gson.Gson;
import com.lzj.blog.properties.FileProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * @author liu
 * @create 2022-07-19 14:03
 * 七牛云工具类
 */

public class FileUtils {

    //上传文件
    public static void uploadFile(byte[] bytes, String fileName,FileProperties properties){
        Configuration cfg = new Configuration(Region.regionCnEast2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        UploadManager uploadManager = new UploadManager(cfg);
        String key = fileName;
        Auth auth = Auth.create(properties.getAccessKey(), properties.getSecretKey());
        String upToken = auth.uploadToken(properties.getBucket());
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
}

