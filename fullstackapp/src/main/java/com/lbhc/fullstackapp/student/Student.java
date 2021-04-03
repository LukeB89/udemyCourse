package com.lbhc.fullstackapp.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Student {


    private final UUID studentId;

//    @NotBlank(message="Fist Name must contain a string")
//    @JsonProperty("firstName")
    @NotBlank
    private final String firstName;

//    @NotBlank(message="Last Name must contain a string")
//    @JsonProperty("lastName")
    @NotBlank
    private final String lastName;

//    @Email(message = "Valid Email Required")
//    @JsonProperty("email")
    @Email
    private final String email;

//    @NotNull(message = "Valid Gender Required")
//    @JsonProperty("gender")
    @NotNull
    private final Gender gender;

    public Student(@JsonProperty("studentId") UUID studentId,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("email") String email,
                   @JsonProperty("gender") Gender gender) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }

    enum Gender {
        MALE, FEMALE
    }



}
