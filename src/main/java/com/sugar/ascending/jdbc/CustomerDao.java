package com.sugar.ascending.jdbc;

import com.sugar.ascending.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //STEP 1: DATABASE info
    private static final String DB_URL = "jdbc:postgresql://localhost:5435/yelp_db"; //not good, can be replaced by hibernate
    private static final String USER = "admin";
    private static final String PASS = "Training123!";

    public List<Customer> getCustomers() {
        logger.info("Enter the method getCustomers");
        List<Customer> customers = new ArrayList<>();
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
            String sql = "SELECT * FROM customer";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int age = rs.getInt("age");


                //Fill the object
                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setAddress(address);
                customer.setEmail(email);
                customer.setAge(age);
                customers.add(customer);
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
        logger.info("Exit the method getCustomer");
        return customers;
    }
    public Customer getCustomerById(int customerId) {
        logger.info("Enter the method getCustomerById");
        Customer customer = new Customer();
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
            String sql = "SELECT * FROM customer where id = "+ customerId;
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int age = rs.getInt("age");


                //Fill the object

                customer.setId(id);
                customer.setName(name);
                customer.setAddress(address);
                customer.setEmail(email);
                customer.setAge(age);

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
        logger.info("Exit the method getCustomerById");
        return customer;
    }


    public void insertCustomer(Customer customer) {
        logger.info("Enter the method insertCustomer");
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
            String sql = "INSERT INTO customer(name,email, address,age) VALUES('"  + customer.getName() + "', '"  + customer.getEmail() +"', '"+
                    customer.getAddress()+"', '" +customer.getAge() + "')";
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

        logger.info("Exit the method insertCustomer");

    }

    public void deleteCustomer(String condition){
        logger.info("Enter the method deleteCustomer");
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
            String sql = "DELETE FROM customer WHERE " + condition;
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
        logger.info("Exit the method deleteCustomer");
    }

    public void updateCustomer(String setStatement, String condition){
        logger.info("Enter the method updateCustomer");
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
            String sql = "UPDATE customer SET "+ setStatement+ " WHERE " + condition;
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
        logger.info("Exit the method updateCustomer");

    }
}

