package com.infosysspringboard.milestone3.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SchedulePickupRequest {
    private Integer prodId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate pickupDate;
}
