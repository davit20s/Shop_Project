package com.test.model;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.test.Enums.Status;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String verification;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private long verificationTime;

    private String resetPasswordCode;

    private long resetPasswordTime;

    @javax.persistence.Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorityList;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    public long getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(long verificationTime) {
        this.verificationTime = verificationTime;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

    public long getResetPasswordTime() {
        return resetPasswordTime;
    }

    public void setResetPasswordTime(long resetPasswordTime) {
        this.resetPasswordTime = resetPasswordTime;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
