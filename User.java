package com.example.gym.model;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "subscription")
    private String subscription;

    @Column(name = "password")
    private String password;

    public User(Long id, String fullName, String nationalId, String email, String phoneNumber, String subscription, String password) {
        this.id = id;
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subscription = subscription;
        this.password = password;
    }

    public User() {

    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", subscription='" + subscription + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
