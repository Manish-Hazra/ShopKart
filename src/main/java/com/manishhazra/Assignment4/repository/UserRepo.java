package com.manishhazra.Assignment4.repository;

import com.manishhazra.Assignment4.model.Role;
import com.manishhazra.Assignment4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {
    User findByEmail(String s);
    List<User> findAllByRole(Role role);
}