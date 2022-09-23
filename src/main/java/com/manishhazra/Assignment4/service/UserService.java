package com.manishhazra.Assignment4.service;

import com.manishhazra.Assignment4.dto.UserDto;
import com.manishhazra.Assignment4.model.Purchase;
import com.manishhazra.Assignment4.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User save(UserDto dto);
    public User saveAdmin(UserDto dto);
    public boolean isRegistered(UserDto dto);
    public List<Purchase> getPurchases(String email);
}
