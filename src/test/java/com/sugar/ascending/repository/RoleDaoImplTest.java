package com.sugar.ascending.repository;

import com.sugar.ascending.init.AppInitializer;
import com.sugar.ascending.model.Business;
import com.sugar.ascending.model.Customer;
import com.sugar.ascending.model.Role;
import org.junit.Assert;
import org.junit.BeforeClass;
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
public class RoleDaoImplTest {
    @Autowired
    private Logger logger;

    @Autowired
    private  RoleDao roleDao;
//    private static BusinessDaoImpl businessDaoImpl;
//    private static CustomerDaoImpl customerDaoImpl;
//    private static Business business;
//    private static Customer customer;

    @BeforeClass
    public static void init() {
//        businessDaoImpl = new BusinessDaoImpl();
//        customerDaoImpl= new CustomerDaoImpl();
//        //first element in both business and customer table, for test purpose only
//        business = businessDaoImpl.getBusinessById(1);
//        customer = customerDaoImpl.getCustomerById(1);
    }

    @Test
    public void getRoles(){
        List<Role> roles = roleDao.getRoles();
        int actualSize =roles.size();
        int expectedSize = 5;
        Assert.assertEquals(actualSize,expectedSize);
    }

}
