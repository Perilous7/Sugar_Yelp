package com.sugar.ascending.repository;

import com.sugar.ascending.model.Business;
import com.sugar.ascending.model.Customer;
import com.sugar.ascending.model.Review;
import com.sugar.ascending.model.Role;
import com.sugar.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RoleDaoImpl implements RoleDao{
//    @Autowired
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(Role role) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = sessionFactory.getCurrentSession();//no need to close manually, create one if none found, get if found
            transaction  = session.beginTransaction();
            session.save(role);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The role %s was inserted into the table.", role.toString()));
        return isSuccess;
    }

    @Override
    public boolean update(Role role) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = sessionFactory.getCurrentSession();//no need to close manually, create one if none found, get if found
            transaction  = session.beginTransaction();
            session.update(role);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The role %s was inserted into the table.", role.toString()));
        return isSuccess;
    }

    @Override
    public boolean delete(String roleName) {
        String hql = "DELETE Role where name = :roleN";
        int deletedCount = 0;
        Transaction transaction = null;


        try{
            Session session = sessionFactory.getCurrentSession();//no need to close manually, create one if none found, get if found
            transaction  = session.beginTransaction();
            Query<Business> query = session.createQuery(hql);
            query.setParameter("roleN",roleName);
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The role %s was deleted.", roleName));
        return deletedCount>=1?true:false;
    }

    @Override
    public List<Role> getRoles() {
        String hql = "FROM Role";
        try(Session session = sessionFactory.openSession()){
            Query<Role> query = session.createQuery(hql);
            return query.list();
        }catch (Exception e){
            logger.error("can't find record",e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Role> getRoleById(int id1, int id2) {
        return null;
    }

    @Override
    public List<Role> getRoleByBusinessId(int bid) {
        return null;
    }

    @Override
    public List<Role> getRoleByCustomerId(int cid) {
        return null;
    }
}
