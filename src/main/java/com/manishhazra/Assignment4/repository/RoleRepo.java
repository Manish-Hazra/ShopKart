package com.manishhazra.Assignment4.repository;

import com.manishhazra.Assignment4.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}