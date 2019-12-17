package com.sugar.ascending.service;

import com.sugar.ascending.model.Customer;
import com.sugar.ascending.repository.CustomerDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private Logger logger;

    public boolean save(Customer customer) {
        return customerDao.save(customer);
    }

    public boolean update(Customer customer) {
        return customerDao.update(customer);
    }

    public boolean delete(String deptName) {
        return customerDao.delete(deptName);
    }

    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    public Customer getCustomerByName(String customerName) {
        return customerDao.getCustomerByName(customerName);
    }

}
