package com.infosysspringboard.milestone3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosysspringboard.milestone3.dto.PickupStatusRequest;
import com.infosysspringboard.milestone3.entity.WasteProduct;
import com.infosysspringboard.milestone3.repository.IWasteProductRepo;
import com.infosysspringboard.milestone3.security.JwtUtil;

@RestController
@RequestMapping("/pickup")
public class PickupController {

    @Autowired
    private IWasteProductRepo productRepo;

    @GetMapping("/my-assignments")
    public List<WasteProduct> getMyPickups() {

        String email = JwtUtil.getLoggedInUserEmail();

        return productRepo.findAll()
                .stream()
                .filter(p ->
                        p.getPickupPerson() != null &&
                        p.getPickupPerson().getUserEmail().equals(email)
                )
                .toList();
    }
    
    @PutMapping("/picked-up")
    public String markProductAsPickedUp(@RequestBody PickupStatusRequest req) {

        String loggedInEmail = JwtUtil.getLoggedInUserEmail();

        WasteProduct product = productRepo.findById(req.getProdId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Ensure only the assigned pickup person can update status
        if (product.getPickupPerson() == null ||
            !product.getPickupPerson().getUserEmail().equals(loggedInEmail)) {
            return "You are not authorized to pick up this product";
        }

        product.setStatus("PICKED_UP");
        productRepo.save(product);

        return "Product picked up successfully";
    }

}
