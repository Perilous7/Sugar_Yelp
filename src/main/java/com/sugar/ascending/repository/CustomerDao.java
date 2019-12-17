package com.sugar.ascending.repository;

import com.sugar.ascending.model.Customer;

import java.util.List;

public interface CustomerDao {
    boolean save(Customer business);
    boolean update(Customer business);
    boolean delete(String businessName);
    List<Customer> getCustomers();
    Customer getCustomerById(int id);
    Customer getCustomerByName(String customerName);
}
