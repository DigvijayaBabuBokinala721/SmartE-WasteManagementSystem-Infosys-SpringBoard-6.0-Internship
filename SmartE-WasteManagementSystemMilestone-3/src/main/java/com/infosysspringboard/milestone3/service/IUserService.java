package com.infosysspringboard.milestone3.service;

import com.infosysspringboard.milestone3.entity.User;

public interface IUserService {
    String save(User user);
    User getUserByEmail(String userEmailId);
    String delete(String userEmailId);
}
