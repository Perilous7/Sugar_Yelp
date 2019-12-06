package com.sugar.ascending.repository;

import com.sugar.ascending.model.Business;
import com.sugar.ascending.model.Customer;
import com.sugar.ascending.model.Review;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReviewDaoImplTest {
    private static ReviewDao reviewDao;
    private static BusinessDaoImpl businessDaoImpl;
    private static CustomerDaoImpl customerDaoImpl;
    private static Business business;
    private static Customer customer;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @BeforeClass
    public static void init() {
        reviewDao = new ReviewDaoImpl();
        businessDaoImpl = new BusinessDaoImpl();
        customerDaoImpl= new CustomerDaoImpl();
        //first element in both business and customer table, for test purpose only
        business = businessDaoImpl.getBusinessById(1);
        customer = customerDaoImpl.getCustomerById(1);
    }

    @Test
    public void getReview() {
        List<Review> reviews = reviewDao.getReviews();
        int expected = 6;
        Assert.assertEquals(expected, reviews.size());

    }

    @Test
    public void getReviewById(){
        List<Review> reviews = reviewDao.getReviewById(1,3);
        for (Review review:reviews) {System.out.println(review);}
    }
    @Test
    public void insertReview() {
        int sizeBefore = reviewDao.getReviews().size();
        Review testReview = new Review(business,customer,5,"testContent");
        reviewDao.save(testReview);

        int sizeAfter = reviewDao.getReviews().size();
        Assert.assertEquals(sizeAfter - 1, sizeBefore);

        reviewDao.delete("testContent");
    }

    @Test
    public void deleteReview() {
        int sizeBefore = reviewDao.getReviews().size();
        Review testReview = new Review(business, customer, 100, "testContent");
        reviewDao.save(testReview);

        reviewDao.delete("testContent");
        int sizeAfter = reviewDao.getReviews().size();
        Assert.assertEquals(sizeAfter, sizeBefore);
    }
    @Test
    public void updateReview(){
        Review testReview = new Review(business,customer,5,"testContent");
        reviewDao.save(testReview);
        String nameBefore= testReview.getContent();
        Review testReview2 = new Review(business,customer, 5, "UpdatedContent");
        reviewDao.update(testReview2);
        String nameAfter= testReview2.getContent();
        Assert.assertNotEquals(nameAfter, nameBefore);
    }

}
