package com.sugar.ascending.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.sugar.ascending.init.AppInitializer;
import com.sugar.ascending.service.FileService;
import com.sugar.ascending.service.FileServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceMockAWSTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AmazonS3 amazonS3;

    @Autowired
    @Spy
    private Logger logger;

    @InjectMocks
    private FileServiceImpl fileService;

    private String bucketName = "training_queue_ascending_com";
    private String fileName = "test.txt";
    private URL fakeFileUrl;

    @Mock
    private MultipartFile multipartFile;

    @Before
    public void setUp() throws IOException{
        logger.info(">>>>>>>>>> Start testing...");

        //Mocks are initialized before each test method
        MockitoAnnotations.initMocks(this);

        fakeFileUrl = new URL("http://www.fakeQueueUrl.com/abc/123/fake");
        //File file = new File("/Users/liweiwang/ascending/lecture/README.md");
        //FileInputStream input = new FileInputStream(file);
        //multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        //path = System.getProperty("user.dir") + File.separator + "temp";

        //Stubbing for the methods in the object multipartFile
        when(multipartFile.getOriginalFilename()).thenReturn("anyFileName");
        when(multipartFile.getContentType()).thenReturn("Application");
        when(multipartFile.getSize()).thenReturn(9999L);
        when(multipartFile.getInputStream()).thenReturn(mock(InputStream.class));


        //Stubbing for the methods doesObjectExist and generatePresignedUrl in the object amazonS3
        when(amazonS3.doesObjectExist(anyString(), anyString())).thenReturn(false);
        when(amazonS3.generatePresignedUrl(any())).thenReturn(fakeFileUrl);
    }
    @After
    public void tearDown() {
        logger.info(">>>>>>>>>> End test");
    }

    @Test
    public void getFileUrl() {
        String fileUrl = fileService.getFileUrl(bucketName, fileName);
        assertEquals(fileUrl, fakeFileUrl.toString());
        verify(amazonS3, times(1)).generatePresignedUrl(any());
    }
}
