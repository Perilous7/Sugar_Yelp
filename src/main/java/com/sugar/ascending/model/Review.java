package com.sugar.ascending.model;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "b_id")
    private Business business;

    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id")
    private Customer customer;

    @Column(name ="rate")
    private int rate;
    @Column(name = "content")
    private String content;
    @Column(name = "create_date")
    private LocalDate date=LocalDate.now();

    public Review() { };

    //for get
    public Review(int id,Business business, Customer customer, int rate, String content,LocalDate date) {
        this.business = business;
        this.date = date;
        this.id = id;
        this.customer = customer;
        this.rate = rate;
        this.content = content;
    }

    //for insert
    public Review(Business business, Customer customer, int rate, String content) {
        this.business = business;
        this.customer =  customer;
        this.rate = rate;
        this.content = content;
    }


    public Review(Business business, Customer customer, int rate, String content,LocalDate date) {
        this.business = business;
        this.customer = customer;
        this.rate = rate;
        this.content = content;
        this.date = date;
    }


    @Override
    public String toString() {
        return "Review{ " +
                "b_id= " + business.getId() +
                ", business name is "+ business.getName() +
                ", c_id= " + customer.getId() +
                "customer name is "+ business.getName() +
                ", rate= " + rate +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
