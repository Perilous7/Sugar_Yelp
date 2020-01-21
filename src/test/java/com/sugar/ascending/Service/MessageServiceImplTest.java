package com.sugar.ascending.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.sugar.ascending.init.AppInitializer;
import com.sugar.ascending.service.MessageService;
import com.sugar.ascending.service.MessageServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {AppInitializer.class}
)
public class MessageServiceImplTest {
    @Autowired
    private MessageService messageService;

    @Autowired
    private AmazonSQS amazonSQS;
    @Test
    public void createQueue(){
        messageService.createQueue("test");
        Assert.assertEquals(amazonSQS.listQueues().getQueueUrls().size(), 1);
    }
}
