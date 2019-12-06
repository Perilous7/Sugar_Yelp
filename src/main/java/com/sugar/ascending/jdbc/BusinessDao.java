package com.sugar.ascending.jdbc;

import com.sugar.ascending.model.Business;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //STEP 1: DATABASE info
    private static final String DB_URL = "jdbc:postgresql://localhost:5435/yelp_db"; //not good, can be replaced by hibernate
    private static final String USER = "admin";
    private static final String PASS = "Training123!";

    public List<Business> getBusinesses() {
        logger.info("Enter the method getBusinesses");
        List<Business> businesses = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2:OPEN CONNECTION
            logger.info("Connecting to database..");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: EXECUTE A QUERY
            logger.info("Create statement..");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM business";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String category = rs.getString("category");
                String hours = rs.getString("hours");


                //Fill the object
                Business business = new Business();
                business.setId(id);
                business.setName(name);
                business.setAddress(address);
                business.setCategory(category);
                business.setHours(hours);
                businesses.add(business);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //STEP 6: Finally block used to close resource
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();

            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        logger.info("Exit the method getBusiness");
        return businesses;
    }

    public Business getBusinessById(int businessId) {
        logger.info("Enter the method getBusinesses");
        Business business = new Business();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2:OPEN CONNECTION
            logger.info("Connecting to database..");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: EXECUTE A QUERY
            logger.info("Create statement..");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM business where id = "+ businessId;
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String category = rs.getString("category");
                String hours = rs.getString("hours");


                //Fill the object

                business.setId(id);
                business.setName(name);
                business.setAddress(address);
                business.setCategory(category);
                business.setHours(hours);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //STEP 6: Finally block used to close resource
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();

            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        logger.info("Exit the method getBusinessById");
        return business;
    }


    public void insertBusiness(Business business) {
        logger.info("Enter the method insertBusiness");
        Connection conn = null;
        Statement stmt = null;
        int affected_rows;

        try {
            //STEP 2:OPEN CONNECTION
            logger.info("Connecting to database..");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: EXECUTE A QUERY
            logger.info("Create statement..");
            stmt = conn.createStatement();
            String sql = "INSERT INTO business(name,address,category,hours) VALUES('"  + business.getName() + "', '"  + business.getAddress() +"', '"+
                    business.getCategory() + "', ' " + business.getHours() + "')";
            logger.info(sql);
            affected_rows = stmt.executeUpdate(sql);
            if ((affected_rows == 1)) {
                logger.info("Record inserted successfully. ");
            } else {
                logger.info("Insert not completed. ");
            }

            if (stmt != null) stmt.close();
            if (conn != null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Exit the method insertBusiness");

    }

    public void deleteBusiness(String condition){
        logger.info("Enter the method deleteBusiness");
        Connection conn = null;
        Statement stmt = null;
        int affected_rows;
        try{
            //STEP 2:OPEN CONNECTION
            logger.info("Connecting to database..");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: EXECUTE A QUERY
            logger.info("Create statement..");
            stmt = conn.createStatement();
            String sql = "DELETE FROM business WHERE " + condition;
            logger.info(sql);
            affected_rows = stmt.executeUpdate(sql);
            if ((affected_rows == 1)) {
                logger.info("Record deleted successfully. ");
            } else {
                logger.info("Deletion not completed. ");
            }
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Exit the method deleteBusiness");
    }



    public void updateBusiness(String setStatement, String condition){
        logger.info("Enter the method updateBusiness");
        Connection conn = null;
        Statement stmt = null;
        int affected_rows;
        try{
            //STEP 2:OPEN CONNECTION
            logger.info("Connecting to database..");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: EXECUTE A QUERY
            logger.info("Create statement..");
            stmt = conn.createStatement();
            String sql = "UPDATE business SET "+ setStatement+ " WHERE " + condition;
            logger.info(sql);
            affected_rows = stmt.executeUpdate(sql);
            if ((affected_rows == 1)) {
                logger.info("Record updated successfully. ");
            } else {
                logger.info("Update not completed. ");
            }


            if (stmt != null) stmt.close();
            if (conn != null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Exit the method updateBusiness");

    }
}
