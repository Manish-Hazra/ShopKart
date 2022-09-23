package com.manishhazra.Assignment4.dto;

import com.manishhazra.Assignment4.model.Preference;
import com.manishhazra.Assignment4.model.Gender;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
    private String email;
    private String firstName;
    private String lastName;
    private Gender sex;
    private String contactNo;
    private String password;
    private Preference preference;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

//    public UserDto(String email, String firstName, String lastName, Gender sex, String contactNo, String password, Preference preference) {
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.sex = sex;
//        this.contactNo = contactNo;
//        this.password = password;
//        this.preference = preference;
//    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getSex() {
        return sex;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getPassword() {
        return password;
    }

    public Preference getPreference() {
        return preference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.email, entity.email) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.sex, entity.sex) &&
                Objects.equals(this.contactNo, entity.contactNo) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.preference, entity.preference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, sex, contactNo, password, preference);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "email = " + email + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "sex = " + sex + ", " +
                "contactNo = " + contactNo + ", " +
                "password = " + password + ", " +
                "preference = " + preference + ")";
    }
}
