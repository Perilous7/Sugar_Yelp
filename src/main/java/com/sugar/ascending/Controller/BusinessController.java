package com.sugar.ascending.Controller;

import com.sugar.ascending.model.Business;
import com.sugar.ascending.model.Review;
import com.sugar.ascending.service.BusinessService;
import com.sugar.ascending.service.ReviewService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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

    //@GetMapping(value = "", produces = "application/json")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Business> getBusinesses() {
        return businessService.getBusinesses();
    }

    @RequestMapping(value = "/{businessName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Review> getReviewByBusinessName(@PathVariable String businessName) {
        Business business = businessService.getBusinessByName(businessName);
        if (business != null) {
            return reviewService.getReviewByBusinessId(business.getId());
        }
        return null;
    }
}
