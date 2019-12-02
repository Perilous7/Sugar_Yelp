package com.sugar.ascending.repository;

import com.sugar.ascending.model.Business;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BusinessDaoImplTest {
    private static businessDao businessDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public static void init() {
        businessDao = new businessDaoImpl();
    }

    @Test
    public void getBusiness() {
        List<Business> businesses = businessDao.getBusiness();
        int expected = 5;
        Assert.assertEquals(expected, businesses.size());

    }
}
