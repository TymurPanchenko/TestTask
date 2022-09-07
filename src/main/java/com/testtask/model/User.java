package com.testtask.model;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "person")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "First_Name", length = 64, nullable = false)
    private String firstName;
    @Column(name = "Last_Name", length = 64, nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Of_Birth", nullable = false)
    private Date dateOfBirth;

    @Override
    public String toString() {
        return "{\"id\": " + id + ", \"firstName\": \"" + firstName + "\", \"lastName\": \""
                + lastName +"\",\"age\":" + Math.floor(TimeUnit.MILLISECONDS.toDays(new Date().getTime() - dateOfBirth.getTime())/365.25) + "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}