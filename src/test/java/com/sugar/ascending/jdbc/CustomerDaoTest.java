package com.sugar.ascending.jdbc;

import com.sugar.ascending.jdbc.CustomerDao;
import com.sugar.ascending.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CustomerDaoTest {
    private CustomerDao customerDao;

    @Before
    public void init(){ customerDao = new CustomerDao();
    }
    @Test
    public void getCustomersTest() {
        List<Customer> customers = customerDao.getCustomers();

        int expectedNumOfCustomers = 4;

        for (Customer customer : customers){
            System.out.println(customer);
        }
        Assert.assertEquals(expectedNumOfCustomers,customers.size());
    }

    @Test
    public void insertCustomerTest(){
        List<Customer> customers = customerDao.getCustomers();
        int NumOfCustomerBeforeInsert = customers.size();

        Customer customer = new Customer();
        customer.setName("cool kid");
        //customer.setAddress("");
        customer.setEmail("iamsocool@gmail.com");
        customer.setAge(15);
        customerDao.insertCustomer(customer);

        List<Customer> customersAfterInsert = customerDao.getCustomers();
        int expectedNumOfCustomerAfterInsert = customersAfterInsert.size();
        Assert.assertEquals(expectedNumOfCustomerAfterInsert-1,NumOfCustomerBeforeInsert);


    }

    @Test
    public void deleteCustomerTest(){
        List<Customer> customers = customerDao.getCustomers();
        int NumOfCustomersBeforeDelete = customers.size();

        String condition = "name = 'cool kid'";
        customerDao.deleteCustomer(condition);

        List<Customer> customersAfterDelete = customerDao.getCustomers();
        int expectedNumOfCustomersAfterDelete = customersAfterDelete.size();
        Assert.assertEquals(expectedNumOfCustomersAfterDelete,NumOfCustomersBeforeDelete-1);
    }

    @Test
    public void updateCustomerTest(){
        List<Customer> customers = customerDao.getCustomers();
        int NumOfCustomerBeforeUpdate = customers.size();

        String setStatement = "name  = 'Cool kid'";
        String condition = "name = 'cool kid'";
        customerDao.updateCustomer(setStatement, condition);

        List<Customer> customersAfterUpdate = customerDao.getCustomers();
        int expectedNumOfCustomerAfterUpdate = customersAfterUpdate.size();
        Assert.assertEquals(expectedNumOfCustomerAfterUpdate,NumOfCustomerBeforeUpdate);
    }
}
