package com.infosysspringboard.milestone3.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "WASTE_PRODUCT")
@NoArgsConstructor
@RequiredArgsConstructor
public class WasteProduct {

    @Id
    @Column(name = "PROD_ID")
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "product_seq",
            initialValue = 1010,
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Integer prodId;

    @Column(name = "PROD_NAME", length = 20, nullable = false)
    @NonNull
    private String prodName;

    @Column(name = "BRAND", length = 20, nullable = false)
    @NonNull
    private String brand;

    @Column(name = "CONDITION", length = 20, nullable = false)
    @NonNull
    private String condition;

    @Column(nullable = false)
    private String status;       

    private LocalDate pickupDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    @JsonBackReference
    @NonNull
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "pickup_person_id")
    private User pickupPerson;

}
