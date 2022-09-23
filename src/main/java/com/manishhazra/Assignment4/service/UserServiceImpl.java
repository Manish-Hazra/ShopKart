package com.manishhazra.Assignment4.service;

import com.manishhazra.Assignment4.dto.UserDto;
import com.manishhazra.Assignment4.model.Purchase;
import com.manishhazra.Assignment4.model.Role;
import com.manishhazra.Assignment4.model.User;
import com.manishhazra.Assignment4.repository.PurchaseRepo;
import com.manishhazra.Assignment4.repository.RoleRepo;
import com.manishhazra.Assignment4.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PurchaseRepo purchaseRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepository,
                           RoleRepo roleRepository,
                           PurchaseRepo purchaseRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.purchaseRepository = purchaseRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User save(UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = new User(dto);
        Role role = Optional.ofNullable(roleRepository.findByName(ROLE_USER)).orElse(new Role(ROLE_USER));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User saveAdmin(UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = new User(dto);
        Role role = Optional.ofNullable(roleRepository.findByName(ROLE_ADMIN)).orElse(new Role(ROLE_ADMIN));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public boolean isRegistered(UserDto dto) {
        return userRepository.findByEmail(dto.getEmail()) != null;
    }

    @Override
    public List<Purchase> getPurchases(String email) {
        return purchaseRepository.findAllByUser(userRepository.findByEmail(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(Collections.singleton(user.getRole())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
