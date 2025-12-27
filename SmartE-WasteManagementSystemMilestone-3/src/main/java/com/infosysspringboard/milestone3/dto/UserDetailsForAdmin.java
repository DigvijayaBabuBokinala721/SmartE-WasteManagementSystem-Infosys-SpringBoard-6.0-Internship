package com.infosysspringboard.milestone3.dto;

import lombok.Data;

@Data
public class UserDetailsForAdmin {

    private Integer userId;
    private String userName;
    private String userEmail;
    private String role;
}
