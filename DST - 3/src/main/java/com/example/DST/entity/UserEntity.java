package com.example.DST.entity;

import com.example.DST.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

import static com.example.DST.constant.ApplicationConstant.PHONE_REGEX;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private int usersId;
    private String userName;
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;
    private String password;
    @Pattern(regexp = PHONE_REGEX, message = "Invalid phone format need 10-11")
    private int phoneNumber;
    private RoleEnum roleEnum;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BookingEntity> bookings;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ReviewEntity> reviews;
}
