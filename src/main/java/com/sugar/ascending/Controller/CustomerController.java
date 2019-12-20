package com.sugar.ascending.Controller;

import com.sugar.ascending.model.Customer;
import com.sugar.ascending.service.CustomerService;
import com.sugar.ascending.service.ReviewService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/customer"})
public class CustomerController {
    private Logger logger;

    private CustomerService customerService;

    private ReviewService reviewService;
    @Autowired
    public CustomerController(Logger logger, CustomerService customerService,ReviewService reviewService){
        this.logger = logger;
        this.customerService = customerService;
        this.reviewService= reviewService;
    }
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String creatCustomer(@RequestBody Customer customer) {
        logger.debug("Customer: " + customer.toString());
        String msg = "The customer was created.";
        boolean isSuccess = customerService.save(customer);

        if (!isSuccess) msg = "The customer was not created.";

        return msg;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @RequestMapping(value = "/name/{customerName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer getCustomerByName(@PathVariable String customerName) {
        return customerService.getCustomerByName(customerName);
    }



}
