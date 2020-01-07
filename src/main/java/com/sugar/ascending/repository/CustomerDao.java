package com.sugar.ascending.repository;

import com.sugar.ascending.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerDao {
    boolean save(Customer business);
    boolean update(Customer business);
    boolean delete(String businessName);
    List<Customer> getCustomers();
    Set<Customer> getCustomersFetch();
    Customer getCustomerById(int id);
    Customer getCustomerByName(String customerName);
    Customer getUserByCredentials(String email, String password);
}
