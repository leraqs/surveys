package com.pp.surveyservice.model;

import lombok.Data;

import java.util.List;


@Data
public class Option {

    private String text;

    private List<User> usersSelected;
}
