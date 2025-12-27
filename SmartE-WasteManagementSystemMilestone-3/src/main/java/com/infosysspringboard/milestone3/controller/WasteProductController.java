package com.infosysspringboard.milestone3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosysspringboard.milestone3.dto.WasteProductRequest;
import com.infosysspringboard.milestone3.entity.User;
import com.infosysspringboard.milestone3.entity.WasteProduct;
import com.infosysspringboard.milestone3.repository.IWasteProductRepo;
import com.infosysspringboard.milestone3.security.JwtUtil;
import com.infosysspringboard.milestone3.service.IUserService;

@RestController
@RequestMapping("/product")
public class WasteProductController {

    @Autowired
    private IWasteProductRepo productRepo;

    @Autowired
    private IUserService userService;

    
    @PostMapping("/add")
    public String addProduct(@RequestBody WasteProductRequest req) {

        String email = JwtUtil.getLoggedInUserEmail();
        User user = userService.getUserByEmail(email);

        WasteProduct product = new WasteProduct(
                req.getProdName(),
                req.getBrand(),
                req.getCondition(),
                user
        );

        product.setStatus("PENDING");  

        productRepo.save(product);
        return "Product added successfully";
    }

    

    //GET LOGGED-IN USER PRODUCTS
    @GetMapping("/my-products")
    public List<WasteProduct> getMyProducts() {

        String email = JwtUtil.getLoggedInUserEmail();
        User user = userService.getUserByEmail(email);

        return user.getWasteProducts();
    }

    
}
