package com.sugar.ascending.jdbc;

import com.sugar.ascending.jdbc.ReviewDao;
import com.sugar.ascending.model.Business;
import com.sugar.ascending.model.Customer;
import com.sugar.ascending.model.Review;
import com.sugar.ascending.jdbc.BusinessDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ReviewDaoTest {
    private ReviewDao reviewDao;
    private BusinessDao businessDao;
    private CustomerDao customerDao;

    @Before
    public void init(){
        reviewDao = new ReviewDao();
        businessDao = new BusinessDao();
        customerDao = new CustomerDao();
    }
    @Test
    public void getReviewsTest() {
        List<Review> reviews = reviewDao.getReviews();

        int expectedNumOfReview = 6;

        for (Review review : reviews){
            System.out.println(review);
        }
        Assert.assertEquals(expectedNumOfReview,reviews.size());
    }

    @Test
    public void insertReviewTest(){
        List<Review> reviews = reviewDao.getReviews();
        int NumOfReviewBeforeInsert = reviews.size();
        Business testBusiness = businessDao.getBusinessById(1);
        Customer testCustomer = customerDao.getCustomerById(1);
        Review review = new Review(testBusiness,testCustomer,5,"delicious food, the service could be better");
        reviewDao.insertReview(review);

        List<Review> reviewsAfterInsert = reviewDao.getReviews();
        int expectedNumOfReviewAfterInsert = reviewsAfterInsert.size();
        Assert.assertEquals(expectedNumOfReviewAfterInsert-1,NumOfReviewBeforeInsert);


    }

    @Test
    public void deleteReviewTest(){
        List<Review> reviews = reviewDao.getReviews();
        int NumOfReviewBeforeDelete = reviews.size();

        String condition = "content = 'delicious food, the service could be better'";
        reviewDao.deleteReview(condition);

        List<Review> reviewsAfterDelete = reviewDao.getReviews();
        int expectedNumOfReviewAfterDelete = reviewsAfterDelete.size();
        Assert.assertEquals(expectedNumOfReviewAfterDelete,NumOfReviewBeforeDelete-1);
    }

    //TODO
    @Test
    public void updateReviewTest(){
        List<Review> reviews = reviewDao.getReviews();
        int NumOfReviewBeforeUpdate = reviews.size();

        String setStatement = "rate = 4";
        String condition = "b_id  = 1 AND c_id=3";
        reviewDao.updateReview(setStatement, condition);

        List<Review> reviewsAfterUpdate = reviewDao.getReviews();
        int expectedNumOfReviewAfterUpdate = reviewsAfterUpdate.size();
        Assert.assertEquals(expectedNumOfReviewAfterUpdate,NumOfReviewBeforeUpdate);
    }
}
