package com.infosysspringboard.milestone3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosysspringboard.milestone3.dto.AdminDeleteUserRequest;
import com.infosysspringboard.milestone3.dto.AssignPickupPersonRequest;
import com.infosysspringboard.milestone3.dto.PickupRequestForAdmin;
import com.infosysspringboard.milestone3.dto.SchedulePickupRequest;
import com.infosysspringboard.milestone3.dto.UserDetailsForAdmin;
import com.infosysspringboard.milestone3.dto.WasteProductDetailsForAdmin;
import com.infosysspringboard.milestone3.entity.User;
import com.infosysspringboard.milestone3.entity.WasteProduct;
import com.infosysspringboard.milestone3.repository.IUserRepository;
import com.infosysspringboard.milestone3.repository.IWasteProductRepo;
import com.infosysspringboard.milestone3.service.EmailService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IUserRepository userRepo;
    
    @Autowired
    private EmailService emailService;


    @Autowired
    private IWasteProductRepo productRepo;

    @GetMapping("/users")
    public List<UserDetailsForAdmin> getAllUsers() {

        return userRepo.findAll()
                .stream()
                .map(u -> {
                    UserDetailsForAdmin dto = new UserDetailsForAdmin();
                    dto.setUserId(u.getUserId());
                    dto.setUserName(u.getUserName());
                    dto.setUserEmail(u.getUserEmail());
                    dto.setRole(u.getRole());
                    return dto;
                })
                .toList();
    }

    
    @GetMapping("/products")
    public List<WasteProductDetailsForAdmin> getAllProducts() {

        return productRepo.findAll()
                .stream()
                .map(p -> {
                    WasteProductDetailsForAdmin dto =
                            new WasteProductDetailsForAdmin();

                    dto.setProdId(p.getProdId());
                    dto.setProdName(p.getProdName());
                    dto.setBrand(p.getBrand());
                    dto.setCondition(p.getCondition());

                    dto.setUserName(p.getUser().getUserName());
                    dto.setUserEmail(p.getUser().getUserEmail());

                    return dto;
                })
                .toList();
    }
    
    
    @GetMapping("/pickup-requests")
    public List<PickupRequestForAdmin> getPickupRequests() {

        return productRepo.findAll().stream().map(p -> {
            PickupRequestForAdmin dto = new PickupRequestForAdmin();
            dto.setProdId(p.getProdId());
            dto.setProdName(p.getProdName());
            dto.setBrand(p.getBrand());
            dto.setCondition(p.getCondition());
            dto.setStatus(p.getStatus());
            dto.setUserName(p.getUser().getUserName());
            dto.setUserEmail(p.getUser().getUserEmail());
            return dto;
        }).toList();
    }
    
    
    @PutMapping("/schedule-pickup")
    public String schedulePickup(@RequestBody SchedulePickupRequest req) {

        WasteProduct product = productRepo.findById(req.getProdId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStatus("APPROVED");
        product.setPickupDate(req.getPickupDate());

        productRepo.save(product);
        
        
        emailService.sendMail(
                product.getUser().getUserEmail(),
                "Pickup Approved",
                "Hello " + product.getUser().getUserName() +
                ",\n\nYour e-waste pickup has been approved.\nPickup Date: "
                + req.getPickupDate() +
                "\n\nThank you!"
            );

            return "Pickup scheduled and email sent";
    }
    
    
    



    
    

    @DeleteMapping("/user")
    public String deleteUser(@RequestBody AdminDeleteUserRequest req) {

        String email = req.getUserEmail();

        if (email == null || email.isBlank()) {
            return "User email is required";
        }

        if (userRepo.findByUserEmail(email) == null) {
            return "User not found";
        }

        userRepo.deleteByUserEmail(email);
        return "User deleted by admin";
    }
    
    
    
    @PutMapping("/assign-pickup-person")
    public String assignPickupPerson(@RequestBody AssignPickupPersonRequest req) {

        WasteProduct product = productRepo.findById(req.getProdId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User pickupPerson = userRepo.findByUserEmail(req.getPickupPersonEmail());

        if (pickupPerson == null) {
            return "Pickup person not found";
        }

        product.setPickupPerson(pickupPerson);
        product.setStatus("ASSIGNED");

        productRepo.save(product);

        return "Pickup person assigned successfully";
    }
    
    
    
}

