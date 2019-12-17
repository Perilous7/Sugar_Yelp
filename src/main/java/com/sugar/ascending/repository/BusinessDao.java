package com.sugar.ascending.repository;

import com.sugar.ascending.model.Business;

import java.util.List;

public interface BusinessDao {
    boolean save(Business business);
    boolean update(Business business);
    boolean delete(String businessName);
    List<Business> getBusinesses();
    Business getBusinessById(int id);
    Business getBusinessByName(String businessId);
}
