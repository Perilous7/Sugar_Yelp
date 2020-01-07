package com.sugar.ascending.init;
import com.sugar.ascending.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan(basePackages =  ("com.sugar.ascending.filter"))
@SpringBootApplication(scanBasePackages = {"com.sugar.ascending"})
    public class AppInitializer {
        public static void main(String[] args) throws NullPointerException{
//            if (HibernateUtil.getSessionFactory() == null) {
//                throw new NullPointerException("The Hibernate session factory is NULL!");
//            }
            SpringApplication.run(AppInitializer.class, args);
        }

}
