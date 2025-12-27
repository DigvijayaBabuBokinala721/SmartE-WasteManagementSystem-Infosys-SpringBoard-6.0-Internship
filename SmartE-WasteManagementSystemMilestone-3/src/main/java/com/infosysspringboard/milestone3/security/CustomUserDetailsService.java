package com.infosysspringboard.milestone3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.infosysspringboard.milestone3.entity.User;
import com.infosysspringboard.milestone3.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByUserEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("User not found: " + email);

        return new UserPrincipal(user);
    }
}
