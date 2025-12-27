package com.infosysspringboard.milestone3.dto;

import lombok.Data;

@Data
public class PickupRequestForAdmin {
	private Integer prodId;
    private String prodName;
    private String brand;
    private String condition;
    private String status;

    private String userName;
    private String userEmail;
}
