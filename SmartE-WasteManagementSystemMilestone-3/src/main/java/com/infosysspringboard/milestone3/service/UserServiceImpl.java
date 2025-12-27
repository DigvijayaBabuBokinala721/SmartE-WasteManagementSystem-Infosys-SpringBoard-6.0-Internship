package com.infosysspringboard.milestone3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infosysspringboard.milestone3.entity.User;
import com.infosysspringboard.milestone3.repository.IUserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {
	
    @Autowired
    private IUserRepository userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;


    
    @Override
    public String save(User user) {

        if (userRepo.findByUserEmail(user.getUserEmail()) != null) {
            return "User already registered";
        }

        // encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("ROLE_USER");

        userRepo.save(user);
        
        
        emailService.sendMail(
        	    user.getUserEmail(),
        	    "Registration Successful",
        	    "Hello " + user.getUserName() + 
        	    ",\n\nYour account has been successfully registered.\n\nThank you!"
        	);

        	return user.getUserName() + " registered successfully";
        
    }



	
    @Override
    public User getUserByEmail(String userEmailId) {
        return userRepo.findByUserEmail(userEmailId);
    }
	
    @Override
    @Transactional
    public String delete(String userEmailId) {

        int rows = userRepo.deleteByUserEmail(userEmailId);

        if (rows == 1) {
            return "User with email " + userEmailId + " has been deleted";
        } else {
            return "User not found with email: " + userEmailId;
        }
    }
}
