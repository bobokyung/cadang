package com.ssafy.cadang.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;
    private String userEmail;
    private Date userBirth;
    private int userHeight;
    private int userWeight;
    private double userCaffeine;
    private double userSugar;
    private int userGender;
    private Date registerDatetime;
    private int userHealth;
    private double maxSugarValue;
    private double maxCaffeineValue;
    private Date maxSugarDate;
    private Date maxCaffeineDate;
}
