package com.infosysspringboard.milestone3.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userEmail;
    private String password;
}
