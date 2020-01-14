package com.sugar.ascending.controller;

import com.sugar.ascending.model.Business;
import com.sugar.ascending.service.BusinessService;
import com.sugar.ascending.service.ReviewService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = {"/business"})
public class BusinessController {
    //@Autowired
    private Logger logger;
    //@Autowired
    private BusinessService businessService;

    private ReviewService reviewService;
    
    @Autowired
    public BusinessController(Logger logger, BusinessService businessService, ReviewService reviewService) {
        this.logger = logger;
        this.businessService = businessService;
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Business> getBusinesses() {
        return businessService.getBusinesses();
    }

    @Cacheable( value = "business")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Business getBusinessById(@PathVariable int id) {
        return businessService.getBusinessById(id);
    }

    @Cacheable( value = "business")
    @RequestMapping(value = "/name/{businessName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Business getBusinessByName(@PathVariable String businessName) {
        return businessService.getBusinessByName(businessName);
    }

    @CacheEvict(value = "business",allEntries = true)
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createBusiness(@RequestBody Business business) {
        logger.debug("Business: " + business.toString());
        String msg = "The business was created.";
        boolean isSuccess = businessService.save(business);
        if (!isSuccess) msg = "The business was not created.";

        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateBusiness(@RequestBody Business business) {
        logger.debug("Business: " + business.toString());
        String msg = "The business was updated.";
        boolean isSuccess = businessService.update(business);
        if (!isSuccess) msg = "The business was not updated.";

        return msg;
    }
    @RequestMapping(value = "/{businessName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteBusiness(@PathVariable String businessName) {
        logger.debug("Business name: " + businessName);
        String msg = "The business was deleted.";
        boolean isSuccess = businessService.delete(businessName);
        if (!isSuccess) msg = "The business was not deleted.";

        return msg;
    }


}
