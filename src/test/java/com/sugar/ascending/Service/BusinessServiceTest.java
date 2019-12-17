package com.sugar.ascending.Service;

import com.sugar.ascending.init.AppInitializer;
import com.sugar.ascending.model.Business;
import com.sugar.ascending.repository.BusinessDao;
import com.sugar.ascending.service.BusinessService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {AppInitializer.class}
)
public class BusinessServiceTest {
    @Autowired
    private BusinessService businessService;

    @Autowired
    private Logger logger;

    @Autowired
    private BusinessDao businessDao;

    @Before
    public void init(){}

    @Test
    public void getBusinessesTest() {
        List<Business> businesses = businessService.getBusinesses();
        int expectedNumOfDept = 5;

        businesses.forEach(dept -> logger.info(dept.toString()));
        Assert.assertEquals(expectedNumOfDept, businesses.size());
    }

    @Test
    public void getBusinessByIdTest(){
        int id = 1;
        Business business = businessService.getBusinessById(id);
        logger.info(business.toString());
        logger.info(business.getName());

        Assert.assertEquals(id, business.getId());
    }

    @Test
    public void updateTest(){
        Business testBusiness = new Business("testName","testRD","testCat","testHours");
        businessDao.save(testBusiness);
        String nameBefore= testBusiness.getName();
        int id = testBusiness.getId();
        Business testBusiness2 = new Business(id, "testName2","testRD","testCat","testHours");
        businessDao.update(testBusiness2);
        String nameAfter= testBusiness2.getName();
        Assert.assertNotEquals(nameAfter, nameBefore);
    }

    @Test
    public void getBusinessByNameTest(){
        String expectedName = "Hot pot city";
        Business business = businessService.getBusinessByName(expectedName);
        logger.info(business.toString());
        logger.info(business.getName());

        Assert.assertEquals(expectedName, business.getName());
    }


}
