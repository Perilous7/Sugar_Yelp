package com.sugar.ascending.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonView({Customer.Advance.class})
    @Column(name = "name")
    private String name;
    @JsonView({Customer.Advance.class})
    @Column(name = "allowed_resource")
    private String allowedResource;
    @JsonView({Customer.Advance.class})
    @Column(name = "allowed_read")
    private boolean allowedRead;
    @JsonView({Customer.Advance.class})
    @Column(name = "allowed_create")
    private boolean allowedCreate;
    @JsonView({Customer.Advance.class})
    @Column(name = "allowed_update")
    private boolean allowedUpdate;
    @JsonView({Customer.Advance.class})
    @Column(name = "allowed_delete")
    private boolean allowedDelete;

    @JsonView({Customer.AnotherBasic.class})
    @ManyToMany(mappedBy = "roles")
    private List<Customer> customers;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAllowedResource() {
        return allowedResource;
    }

    public boolean isAllowedRead() { return allowedRead;}

    public boolean isAllowedCreate() {
        return allowedCreate;
    }

    public boolean isAllowedUpdate() {
        return allowedUpdate;
    }

    public boolean isAllowedDelete() {
        return allowedDelete;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", allowedResource='" + allowedResource + '\'' +
                ", allowedRead=" + allowedRead +
                ", allowedCreate=" + allowedCreate +
                ", allowedUpdate=" + allowedUpdate +
                ", allowedDelete=" + allowedDelete +
                ", customers=" + customers +
                '}';
    }
}