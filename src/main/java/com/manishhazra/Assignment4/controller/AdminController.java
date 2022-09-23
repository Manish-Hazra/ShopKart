package com.manishhazra.Assignment4.controller;

import com.manishhazra.Assignment4.dto.ProductDto;
import com.manishhazra.Assignment4.dto.DiscountDto;
import com.manishhazra.Assignment4.dto.UserDto;
import com.manishhazra.Assignment4.service.ProductService;
import com.manishhazra.Assignment4.service.DiscountService;
import com.manishhazra.Assignment4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final ProductService apparelService;
    private final DiscountService dealService;

    public AdminController(UserService userService, ProductService apparelService, DiscountService dealService) {
        this.userService = userService;
        this.apparelService = apparelService;
        this.dealService = dealService;
    }

    @ModelAttribute("user")
    UserDto userDto() {
        return new UserDto();
    }

    @ModelAttribute("apparel")
    ProductDto apparelDto()
    {
        return new ProductDto();
    }

    @ModelAttribute("deal")
    DiscountDto dealDto()
    {
        return new DiscountDto();
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") UserDto dto) {
        if (userService.isRegistered(dto))
            return "redirect:/admin/signup?error";
        userService.saveAdmin(dto);
        return "redirect:/admin/signup?success";
    }

    @GetMapping("")
    public String adminPanel(@RequestParam(required = false) String q, Model model) {
        if (q == null || q.isEmpty())
            model.addAttribute("apparelList", apparelService.listApparel()
                    .stream().map(ProductDto::new)
                    .peek(apparelDto -> apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId())))
                    .collect(Collectors.toList()));
        else {
            model.addAttribute("apparelList", apparelService.listApparel()
                    .stream()
                    .filter(apparel -> apparel.getBrandName().toLowerCase(Locale.ROOT).startsWith(q.toLowerCase(Locale.ROOT)) || apparel.getGenericName().toLowerCase(Locale.ROOT).startsWith(q.toLowerCase(Locale.ROOT)))
                    .map(ProductDto::new)
                    .peek(apparelDto -> apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId())))
                    .collect(Collectors.toList()));
        }
        return "admin";
    }

}
