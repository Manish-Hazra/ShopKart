package com.manishhazra.Assignment4.controller;

import com.manishhazra.Assignment4.dto.DiscountDto;
import com.manishhazra.Assignment4.model.Discount;
import com.manishhazra.Assignment4.service.DiscountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/deal")
public class DiscountController {
    private final DiscountService dealService;


    public DiscountController(DiscountService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("")
    String createDeal(@ModelAttribute("deal") DiscountDto dto)
    {
        if(dto.getValidFrom() == null || dto.getValidFrom().isEmpty())
        {
            dto.setValidFrom(LocalDateTime.now().toString());
        }

        Discount deal = dealService.save(dto);
        if(deal == null)
        {
            return "redirect:/admin?dealError";
        }
        return "redirect:/admin?dealSuccess";
    }


}
