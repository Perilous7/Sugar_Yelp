package com.sugar.ascending.jdbc;

import com.sugar.ascending.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //STEP 1: DATABASE info
    private static final String DB_URL = "jdbc:postgresql://localhost:5435/yelp_db"; //not good, can be replaced by hibernate
    private static final String USER = "admin";
    private static final String PASS = "Training123!";

    public List<Review> getReviews() {
        logger.info("Enter the method getReviews");
        List<Review> reviews = new ArrayList<>();
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
            String sql = "SELECT * FROM review";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int b_id = rs.getInt("b_id");
                int rate = rs.getInt("rate");
                String content = rs.getString("content");
                int c_id = rs.getInt("c_id");


                //Fill the object
                Review review = new Review();
                review.setB_id(b_id);
                review.setC_id(c_id);
                review.setContent(content);
                review.setRate(rate);
                reviews.add(review);
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
        return reviews;
    }

    public void insertReview(Review review) {
        logger.info("Enter the method insertReview");
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
            String sql = "INSERT INTO review(b_id,c_id, rate,content) VALUES('"  + review.getB_id() + "', '"  + review.getC_id() +"', '"+
                    review.getRate()+"', '" +review.getContent() + "')";
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

        logger.info("Exit the method insertReview");

    }

    public void deleteReview(String condition){
        logger.info("Enter the method deleteReview");
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
            String sql = "DELETE FROM review WHERE " + condition;
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
        logger.info("Exit the method deleteReview");
    }

    public void updateReview(String setStatement, String condition){
        logger.info("Enter the method updateReview");
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
            String sql = "UPDATE review SET "+ setStatement+ " WHERE " + condition;
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
        logger.info("Exit the method updateReview");

    }
}

