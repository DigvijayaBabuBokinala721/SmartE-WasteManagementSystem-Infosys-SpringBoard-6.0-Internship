package com.infosysspringboard.milestone3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.infosysspringboard.milestone3.entity.User;

import jakarta.transaction.Transactional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    User findByUserEmail(String userEmail);

    @Modifying
    @Transactional
    int deleteByUserEmail(String userEmail);
}
