package com.sugar.ascending.repository;

import com.sugar.ascending.model.Business;
import com.sugar.ascending.model.Customer;
import com.sugar.ascending.model.Review;
import com.sugar.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Review review) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();//no need to close manually, create one if none found, get if found
            //session = HibernateUtil.getSessionFactory().openSession();// create new one, close manually
            transaction  = session.beginTransaction();
            session.save(review);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The review %s was inserted into the table.", review.toString()));
        return isSuccess;
    }

    @Override
    public boolean update(Review review) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();//no need to close manually, create one if none found, get if found
            //session = HibernateUtil.getSessionFactory().openSession();// create new one, close manually
            transaction  = session.beginTransaction();
            session.update(review);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The review %s was updated.", review.toString()));
        return isSuccess;
    }

    @Override
    public boolean delete(String reviewName) {
        String hql = "DELETE Review where content = :reviewN";
        int deletedCount = 0;
        Transaction transaction = null;


        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();//no need to close manually, create one if none found, get if found
            transaction  = session.beginTransaction();
            Query<Review> query = session.createQuery(hql);
            query.setParameter("reviewN",reviewName);
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The review %s was deleted.", reviewName));
        return deletedCount>=1?true:false;
    }

    @Override
    public List<Review> getReviews() {
        String hql = "FROM Review";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Review> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public  List<Review> getReviewById(int businessId, int customerId)
    {
//        BusinessDao businessDao = new BusinessDaoImpl();
//        Business business = businessDao.getBusinessById(b_id);
//        CustomerDao customerDao = new CustomerDaoImpl();
//        Customer customer = customerDao.getCustomerById(c_id);
        String hql = "FROM Review r join fetch r.business join fetch r.customer where r.business.id = :b and r.customer.id = :c";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Review> query = session.createQuery(hql);
            query.setParameter("b",businessId);
            query.setParameter("c",customerId);
            return query.list();
        }
    }
}
