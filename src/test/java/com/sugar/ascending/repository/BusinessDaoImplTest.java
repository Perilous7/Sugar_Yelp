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
        int expected = 5;
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
        int sizeBefore = businessDao.getBusinesses().size();
        Business testBusiness = new Business(3,"testName","testRD","testCat","testHours");
        businessDao.update(testBusiness);
        String nameBefore= businessDao.getBusinesses().get(sizeBefore-1).getName();
        Business testBusiness2 = new Business(3,"Ascending",
                "80 E Jefferson St #300D, Falls Church, VA 22046",
                "study center",
                "Monday 9:30AM–6:30PM; Tuesday 9:30AM–6:30PM; Wednesday 9:30AM–6:30PM; Thursday 9:30AM–6:30PM; Friday 9:30AM–6:30PM; Saturday 10AM–5:30PM Sunday Closed");
        businessDao.update(testBusiness2);
        String nameAfter= businessDao.getBusinesses().get(sizeBefore-1).getName();
        int sizeAfter = businessDao.getBusinesses().size();
        Assert.assertNotEquals(nameAfter, nameBefore);
    }


}
