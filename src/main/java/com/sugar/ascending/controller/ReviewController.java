package com.sugar.ascending.controller;

import com.sugar.ascending.model.Business;
import com.sugar.ascending.model.Customer;
import com.sugar.ascending.model.Review;
import com.sugar.ascending.service.BusinessService;
import com.sugar.ascending.service.CustomerService;
import com.sugar.ascending.service.ReviewService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/review"})
public class ReviewController {
    private BusinessService businessService;

    private ReviewService reviewService;

    private CustomerService customerService;

    private Logger logger;
    @Autowired
    public ReviewController(Logger logger, CustomerService customerService, ReviewService reviewService,BusinessService businessService){
        this.logger = logger;
        this.customerService = customerService;
        this.reviewService= reviewService;
        this.businessService = businessService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Review> getReviews(){
        return reviewService.getReviews();
    }

    @RequestMapping(value = "/business/{businessName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Review> getReviewByBusinessName(@PathVariable String businessName) {
        Business business = businessService.getBusinessByName(businessName);
        if (business != null) {
            return reviewService.getReviewByBusinessId(business.getId());
        }
        return null;
    }

    @RequestMapping(value = "/customer/{customerName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Review> getReviewByCustomerName(@PathVariable String customerName) {
        Customer customer = customerService.getCustomerByName(customerName);
        if (customer != null) {
            return reviewService.getReviewByCustomerId(customer.getId());
        }
        return null;
    }

    @RequestMapping(value = "/byName/{businessName}/{customerName}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createReviewByName(@PathVariable String businessName, @PathVariable String customerName, @RequestBody Review review) {
        String msg = "The review was created.";
        review.setBusiness(businessService.getBusinessByName(businessName));
        review.setCustomer(customerService.getCustomerByName(customerName));
        logger.debug("review: " + review.toString());

        boolean isSuccess = reviewService.save(review);
        if (!isSuccess) msg = "The review was not created.";

        return msg;
    }

    @RequestMapping(value = "/byId/{businessId}/{customerId}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createReviewById(@PathVariable int businessId, @PathVariable int customerId, @RequestBody Review review) {
        String msg = "The review was created.";
        review.setBusiness(businessService.getBusinessById(businessId));
        review.setCustomer(customerService.getCustomerById(customerId));
        logger.debug("review: " + review.toString());
        boolean isSuccess = reviewService.save(review);
        if (!isSuccess) msg = "The review was not created.";

        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createReview(@RequestBody Review review) {
        logger.debug("review: " + review.toString());
        String msg = "The review was created.";
        boolean isSuccess = reviewService.save(review);
        if (!isSuccess) msg = "The review was not created.";

        return msg;
    }
}
