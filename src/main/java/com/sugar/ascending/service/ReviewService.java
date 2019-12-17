package com.sugar.ascending.service;

import com.sugar.ascending.model.Review;
import com.sugar.ascending.repository.ReviewDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ReviewService {
    @Autowired
    private ReviewDao serviceDao;

    @Autowired
    private Logger logger;

    public boolean save(Review service) {
        return serviceDao.save(service);
    }

    public boolean update(Review service) {
        return serviceDao.update(service);
    }

    public boolean delete(String deptName) {
        return serviceDao.delete(deptName);
    }

    public List<Review> getReviews() {
        return serviceDao.getReviews();
    }

    public List<Review> getReviewById(int bId, int cId) {
        return serviceDao.getReviewById(bId, cId);
    }
    public List<Review> getReviewByBusinessId(int bId) {
        return serviceDao.getReviewByBusinessId(bId);
    }
    public List<Review> getReviewByCustomerId(int cId) {
        return serviceDao.getReviewByCustomerId(cId);
    }

}