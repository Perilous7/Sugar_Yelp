package com.sugar.ascending.Controller;

import com.sugar.ascending.model.Customer;
import com.sugar.ascending.model.Review;
import com.sugar.ascending.service.CustomerService;
import com.sugar.ascending.service.ReviewService;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/customer"})
public class CustomerController {
    private Logger logger;

    private CustomerService customerService;

    private ReviewService reviewService;
    public CustomerController(Logger logger, CustomerService customerService,ReviewService reviewService){
        this.logger = logger;
        this.customerService = customerService;
        this.reviewService= reviewService;
    }
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/{customerName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Review> getReviewByCustomerName(@PathVariable String customerName) {
        Customer customer = customerService.getCustomerByName(customerName);
        if (customer != null) {
            return reviewService.getReviewByCustomerId(customer.getId());
        }
        return null;
    }

}
