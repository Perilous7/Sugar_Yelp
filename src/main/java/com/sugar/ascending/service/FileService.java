package com.sugar.ascending.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(String bucketName, MultipartFile file)throws IOException;
    String getFileUrl(String bucketName, String fileName);
    void deleteFile(String bucketName,String object_key);
    void createBucket(String bucketName);
    boolean saveFile(MultipartFile multipartFile, String filePath);

}
