package com.sugar.ascending.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.sugar.ascending.init.AppInitializer;
import com.sugar.ascending.service.FileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {AppInitializer.class}
)
public class FileServiceImplTest {
    @Autowired
    private FileService fileService;

    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private Logger logger;

    private String bucketName = "sugar-bucket.ascending.com";
    private String fileName = "test.txt";
    private MultipartFile multipartFile;
    private String path;

    @Before
    public void setUp() throws IOException {
        logger.info(">>>>>>>>>> Start testing...");

        File file = new File("/home/sugar/amazonTestFile.txt");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", input);
        path = System.getProperty("user.dir") + File.separator + "temp";
    }

    @Test
    public void fileUploadTest() throws IOException {
        String fileUrl = fileService.uploadFile(bucketName, multipartFile);
        Assert.assertNotNull(fileUrl);
        //tear down
        fileService.deleteFile(bucketName,multipartFile.getOriginalFilename());
    }

    @Test
    public void deleteFileTest() throws IOException{
        fileService.uploadFile(bucketName,multipartFile);
        int sizeBefore = amazonS3.listObjectsV2(bucketName).getObjectSummaries().size();

        fileService.deleteFile(bucketName,multipartFile.getOriginalFilename());
        int sizeAfter = amazonS3.listObjectsV2(bucketName).getObjectSummaries().size();
        Assert.assertEquals(sizeAfter,sizeBefore-1);
    }

    @Test
    public void createBucketTest(){
        fileService.createBucket(bucketName);
        List<Bucket> buckets = amazonS3.listBuckets();
        Assert.assertNotEquals(buckets.size(),0);
    }

    @Test
    public void saveFileTest(){
        Assert.assertEquals(fileService.saveFile(multipartFile,path),true);
    }
}
