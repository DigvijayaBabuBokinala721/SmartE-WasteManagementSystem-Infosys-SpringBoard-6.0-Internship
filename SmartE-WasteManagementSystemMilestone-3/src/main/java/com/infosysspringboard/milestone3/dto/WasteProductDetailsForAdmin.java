package com.infosysspringboard.milestone3.dto;

import lombok.Data;

@Data
public class WasteProductDetailsForAdmin {

    private Integer prodId;
    private String prodName;
    private String brand;
    private String condition;

    // user details (safe fields only)
    private String userName;
    private String userEmail;
}
