package com.graduation.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private Integer id;
    private String account;
    private String name;
    private Integer type;
    private String token;
}
