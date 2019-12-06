package com.sugar.ascending.repository;

import com.sugar.ascending.model.Customer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerDaoImplTest {
    private static CustomerDao customerDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public static void init() {
        customerDao = new CustomerDaoImpl();
    }

    @Test
    public void getCustomer() {
        List<Customer> customers = customerDao.getCustomers();
        int expected = 4;
        Assert.assertEquals(expected, customers.size());

    }

    @Test
    public void insertCustomer() {
        int sizeBefore = customerDao.getCustomers().size();
        Customer testCustomer = new Customer("testName", "testRD", "testCat", 100);
        customerDao.save(testCustomer);

        int sizeAfter = customerDao.getCustomers().size();
        Assert.assertEquals(sizeAfter - 1, sizeBefore);

        customerDao.delete("testName");
    }

    @Test
    public void deleteCustomer() {
        int sizeBefore = customerDao.getCustomers().size();
        Customer testCustomer = new Customer("testName", "testRD", "testCat", 100);
        customerDao.save(testCustomer);

        customerDao.delete("testName");
        int sizeAfter = customerDao.getCustomers().size();
        Assert.assertEquals(sizeAfter, sizeBefore);
    }
    @Test
    public void updateCustomer(){
        int sizeBefore = customerDao.getCustomers().size();
        Customer testCustomer = new Customer(3,"testName","testRD","testCat",100);
        customerDao.update(testCustomer);
        String nameBefore= customerDao.getCustomers().get(sizeBefore-1).getName();
        Customer testCustomer2 = new Customer(3,"Ascending",
                "80 E Jefferson St #300D, Falls Church, VA 22046",
                "study center", 100);
        customerDao.update(testCustomer2);
        String nameAfter= customerDao.getCustomers().get(sizeBefore-1).getName();
        int sizeAfter = customerDao.getCustomers().size();
        Assert.assertNotEquals(nameAfter, nameBefore);
    }

    @Test
    public void getCustomerByIdTest(){
        Customer customer = customerDao.getCustomerById(1);
        System.out.println(customer);
    }

}
