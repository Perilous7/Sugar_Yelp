package com.sugar.ascending.model;
import org.hibernate.query.criteria.internal.BasicPathUsageException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "business")
public class Business {

    public Business(){};
    public Business(String name, String address, String category, String hours) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.hours = hours;
    }
    public Business(int id,String name, String address, String category, String hours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.hours = hours;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "category")
    private String category;

    @Column(name = "hours")
    private String hours;

    @OneToMany(mappedBy = "business")
    private List<Review> reviews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", hours='" + hours + '\'' +
                '}';
    }
}
