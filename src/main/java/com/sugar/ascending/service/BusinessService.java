package com.sugar.ascending.service;

import com.sugar.ascending.model.Business;
import com.sugar.ascending.repository.BusinessDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BusinessService {
    @Autowired
    private BusinessDao businessDao;

    @Autowired
    private Logger logger;

    public boolean save(Business business){return businessDao.save(business); }

    public boolean update(Business business) {
        return businessDao.update(business);
    }

    public boolean delete(String deptName) {
        return businessDao.delete(deptName);
    }

    public List<Business> getBusinesses() {
        return businessDao.getBusinesses();
    }
    public Business getBusinessById(int id) {
        return businessDao.getBusinessById(id);
    }

    public Business getBusinessByName(String businessName){  return businessDao.getBusinessByName(businessName);}


}
