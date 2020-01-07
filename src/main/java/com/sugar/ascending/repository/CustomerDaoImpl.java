package com.sugar.ascending.repository;

import com.sugar.ascending.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired private Logger logger ;
    
    @Autowired private SessionFactory sessionFactory;

    @Override
    public boolean save(Customer customer) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = sessionFactory.getCurrentSession();//no need to close manually, create one if none found, get if found
            //session = sessionFactory.openSession();// create new one, close manually
            transaction  = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The customer %s was inserted into the table.", customer.toString()));
        return isSuccess;
    }

    @Override
    public boolean update(Customer customer) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = sessionFactory.getCurrentSession();//no need to close manually, create one if none found, get if found
            //session = sessionFactory.openSession();// create new one, close manually
            transaction  = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The customer %s was updated.", customer.toString()));
        return isSuccess;
    }

    @Override
    public boolean delete(String customerName) {
        String hql = "DELETE Customer where name = :customerN";
        int deletedCount = 0;
        Transaction transaction = null;


        try{
            Session session = sessionFactory.getCurrentSession();//no need to close manually, create one if none found, get if found
            transaction  = session.beginTransaction();
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("customerN",customerName);
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The customer %s was deleted.", customerName));
        return deletedCount>=1?true:false;
    }

    @Override
    public List<Customer> getCustomers() {
        String hql = "FROM Customer";
        try(Session session = sessionFactory.openSession()){
            Query<Customer> query = session.createQuery(hql);
            return query.list();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
    @Override
    public Set<Customer> getCustomersFetch() {
        String hql = "FROM Customer c join fetch c.roles";
        try(Session session = sessionFactory.openSession()){
            Query<Customer> query = session.createQuery(hql);
            return query.list().stream().collect(Collectors.toSet());
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        String hql = "FROM Customer c where c.id = :id ";
        try(Session session = sessionFactory.openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("id",id);
            return query.uniqueResult();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        String hql = "FROM Customer c where c.name = :name";
        try(Session session = sessionFactory.openSession()){
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("name",customerName);
            return query.uniqueResult();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getUserByCredentials(String email, String password) {
        String hql = "FROM Customer as u where lower(u.email) = :email and u.password = :password";
        logger.debug(String.format("User email: %s, password: %s", email, password));

        try (Session session = sessionFactory.openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);

            return query.uniqueResult();
        }
    }

}
