package com.sugar.ascending.repository;

import com.sugar.ascending.model.Business;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class BusinessDaoImplTest {
    private static BusinessDao businessDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public static void init() {
        businessDao = new BusinessDaoImpl();
    }

    @Test
    public void getBusiness() {
        List<Business> businesses = businessDao.getBusinesses();
        int expected = 6;
        Assert.assertEquals(expected, businesses.size());

    }
    @Test
    public void getBusinessById() {
        Business business = businessDao.getBusinessById(1);
        System.out.println(business);

    }

    @Test
    public void insertBusiness() {
        int sizeBefore = businessDao.getBusinesses().size();
        Business testBusiness = new Business("testName","testRD","testCat","testHours");
        businessDao.save(testBusiness);

        int sizeAfter = businessDao.getBusinesses().size();
        Assert.assertEquals(sizeAfter-1, sizeBefore);

        businessDao.delete("testName");
    }

    @Test
    public void deleteBusiness() {
        int sizeBefore = businessDao.getBusinesses().size();
        Business testBusiness = new Business("testName","testRD","testCat","testHours");
        businessDao.save(testBusiness);

        businessDao.delete("testName");
        int sizeAfter = businessDao.getBusinesses().size();
        Assert.assertEquals(sizeAfter, sizeBefore);

    }

    @Test
    public void updateBusiness(){
        Business testBusiness = new Business("testName","testRD","testCat","testHours");
        businessDao.save(testBusiness);
        String nameBefore= testBusiness.getName();
        int id = testBusiness.getId();
        Business testBusiness2 = new Business(id, "testName2","testRD","testCat","testHours");
        businessDao.update(testBusiness2);
        String nameAfter= testBusiness2.getName();
        Assert.assertNotEquals(nameAfter, nameBefore);
    }


}
