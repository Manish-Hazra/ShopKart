package com.manishhazra.Assignment4.controller;

import com.manishhazra.Assignment4.dto.UserDto;
import com.manishhazra.Assignment4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    UserDto userDto()
    {
        return new UserDto();
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") UserDto dto) {
        if(userService.isRegistered(dto))
            return "redirect:/signup?error";
        userService.save(dto);
        return "redirect:/signup?success";
    }
}
