package com.sugar.ascending.jdbc;

import com.sugar.ascending.jdbc.BusinessDao;
import com.sugar.ascending.model.Business;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BusinessDaoTest {
    private BusinessDao businessDao;

    @Before
    public void init(){
        businessDao = new BusinessDao();
    }
    @Test
    public void getBusinessesTest() {
        List<Business> businesses = businessDao.getBusinesses();

        int expectedNumOfBusiness = 5;

        for (Business business : businesses){
            System.out.println(business);
        }
        Assert.assertEquals(expectedNumOfBusiness,businesses.size());
    }

    @Test
    public void insertBusinessTest(){
        List<Business> businesses = businessDao.getBusinesses();
        int NumOfBusinessBeforeInsert = businesses.size();
        //create test business
        Business testb = new Business();
        testb.setName("test");
        testb.setAddress("some rd");
        testb.setCategory("food");
        testb.setHours("Five days a week");
        businessDao.insertBusiness(testb);

        List<Business> businessesAfterInsert = businessDao.getBusinesses();
        int expectedNumOfBusinessAfterInsert = businessesAfterInsert.size();
        Assert.assertEquals(expectedNumOfBusinessAfterInsert-1,NumOfBusinessBeforeInsert);
        //delete test business
        businessDao.deleteBusiness("name = 'test'");

    }

    @Test
    public void deleteBusinessTest(){
        List<Business> businesses = businessDao.getBusinesses();
        int NumOfBusinessBeforeDelete = businesses.size();

        //delete one record as test
        String condition = "name = 'Peter Chang'";
        businessDao.deleteBusiness(condition);

        List<Business> businessesAfterDelete = businessDao.getBusinesses();
        int expectedNumOfBusinessAfterDelete = businessesAfterDelete.size();
        Assert.assertEquals(expectedNumOfBusinessAfterDelete,NumOfBusinessBeforeDelete-1);

        //add back
        Business b = new Business();
        b.setName("Peter Chang");
        b.setAddress("2503-E N Harrison St, Arlington, VA 22207");
        b.setCategory("Good restaurant");
        b.setHours("Monday 11:30AM–3PM, 5–9:45PM; Tuesday 11:30AM–3PM, 5–9:45PM; Wednesday 11:30AM–3PM, 5–9:45PM; Thursday 11:30AM–3PM, 5–9:45PM;Friday 11:30AM–9:45PM; Saturday 11:30AM–9:45PM; Sunday 11:30AM–8:45PM");

        businessDao.insertBusiness(b);

    }

    @Test
    public void updateBusinessTest(){
        //create test data
        Business testb = new Business();
        testb.setName("test");
        testb.setAddress("some rd");
        testb.setCategory("food");
        testb.setHours("Five days a week");
        businessDao.insertBusiness(testb);
        List<Business> businesses = businessDao.getBusinesses();
        int NumOfBusinessBeforeUpdate = businesses.size();

        String setStatement = "category  = 'Good restaurant'";
        String condition = "name = 'test'";
        businessDao.updateBusiness(setStatement, condition);

        List<Business> businessesAfterUpdate = businessDao.getBusinesses();
        int expectedNumOfBusinessAfterUpdate = businessesAfterUpdate.size();
        Assert.assertEquals(expectedNumOfBusinessAfterUpdate,NumOfBusinessBeforeUpdate);
        //delete test data
        businessDao.deleteBusiness(condition);
    }

}
