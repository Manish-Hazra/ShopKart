package com.manishhazra.Assignment4.controller;

import com.manishhazra.Assignment4.dto.ProductDto;
import com.manishhazra.Assignment4.model.Product;
import com.manishhazra.Assignment4.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/apparel")
public class ProductController {

    private ProductService apparelService;

    public ProductController(ProductService apparelService) {
        this.apparelService = apparelService;
    }

    @PostMapping("")
    String createApparel(@ModelAttribute("apparel") ProductDto dto)
    {
        Product apparel = apparelService.save(dto);
        if(apparel == null)
        {
            return "redirect:/admin?apparelError";
        }
        return "redirect:/admin?apparelSuccess";
    }

}
