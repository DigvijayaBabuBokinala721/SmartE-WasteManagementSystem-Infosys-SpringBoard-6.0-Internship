package com.infosysspringboard.milestone3.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "Client")
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @Column(name = "USER_ID")  
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            initialValue = 1010,
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Integer userId;

    @Column(name = "USER_NAME", length = 30, nullable = false)
    @NonNull
    private String userName;

    @Column(name = "USER_EMAIL", length = 40, nullable = false, unique = true)
    @NonNull
    private String userEmail;
    
    @Column(nullable = false)
    private String password;

    @Column(name = "MOBILE_NO", nullable = false)
    @NonNull
    private Long mobileNo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WasteProduct> wasteProducts;
    
    @Column(nullable = false)
    private String role;   

    
    
}
