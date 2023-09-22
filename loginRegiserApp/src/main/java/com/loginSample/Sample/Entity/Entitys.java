package com.loginSample.Sample.Entity;

import jakarta.persistence.*;


import jakarta.persistence.Entity;

@Entity
@Table(name = "allDetails")
public class Entitys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "Role" ,columnDefinition = "varchar(10) default 'USER'")
    private String role = "USER";


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Entitys() {
    }

    public Entitys(String name, String email,  String password,String role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
