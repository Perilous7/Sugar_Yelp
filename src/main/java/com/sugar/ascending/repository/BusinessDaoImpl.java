package com.sugar.ascending.repository;

import com.sugar.ascending.model.Business;
import com.sugar.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessDaoImpl implements BusinessDao {
    @Autowired private Logger logger;

    @Override
    public boolean save(Business business) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();//no need to close manually, create one if none found, get if found
            //session = HibernateUtil.getSessionFactory().openSession();// create new one, close manually
            transaction  = session.beginTransaction();
            session.save(business);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The business %s was inserted into the table.", business.toString()));
        return isSuccess;
    }

    @Override
    public boolean update(Business business) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();//no need to close manually, create one if none found, get if found
            //session = HibernateUtil.getSessionFactory().openSession();// create new one, close manually
            transaction  = session.beginTransaction();
            session.update(business);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The business %s was updated.", business.toString()));
        return isSuccess;
    }

    @Override
    public boolean delete(String businessName) {
        String hql = "DELETE Business where name = :businessN";
        int deletedCount = 0;
        Transaction transaction = null;


        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();//no need to close manually, create one if none found, get if found
            transaction  = session.beginTransaction();
            Query<Business> query = session.createQuery(hql);
            query.setParameter("businessN",businessName);
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The business %s was deleted.", businessName));
        return deletedCount>=1?true:false;
    }

    @Override
    public List<Business> getBusinesses() {
        String hql = "FROM Business";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Business> query = session.createQuery(hql);
            return query.list();
        }
    }
    @Override
    public Business getBusinessById(int id) {
        String hql = "FROM Business b where b.id=:id";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Business> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

    @Override
    public Business getBusinessByName(String businessName) {
        String hql = "FROM Business b where b.name = :name";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Business> query = session.createQuery(hql);
            query.setParameter("name", businessName);

            return query.getSingleResult();
        }

    }
}
