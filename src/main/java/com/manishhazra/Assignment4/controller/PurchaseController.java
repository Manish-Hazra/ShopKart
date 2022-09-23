package com.manishhazra.Assignment4.controller;

import com.manishhazra.Assignment4.dto.PurchaseDto;
import com.manishhazra.Assignment4.metric.MVCalc;
import com.manishhazra.Assignment4.model.Purchase;
import com.manishhazra.Assignment4.service.PurchaseService;
import com.manishhazra.Assignment4.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final UserService userService;


    PurchaseController(PurchaseService purchaseService, UserService userService)
    {
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    @GetMapping("/purchases")
    String purchaseList(@RequestParam(required = false) String q, Model model, Authentication auth)
    {
        User user = (User) auth.getPrincipal();
        List<PurchaseDto> purchases = userService
                .getPurchases(user.getUsername())
                .stream()
                .map(PurchaseDto::new)
                .collect(Collectors.toList());
        if(q != null && !q.isEmpty())
        {
            purchases = purchases.stream().filter(purchase ->
                    purchase.getApparel().getBrandName().toLowerCase(Locale.ROOT).startsWith(q.toLowerCase(Locale.ROOT))
                    || purchase.getApparel().getGenericName().toLowerCase(Locale.ROOT).startsWith(q.toLowerCase(Locale.ROOT))
            ).collect(Collectors.toList());
        }
        model.addAttribute("purchases", purchases);
        return "purchases";
    }

    @PostMapping("/purchase")
    String purchase(@RequestParam UUID apparelId, @SessionAttribute MVCalc metric, Authentication auth)
    {
        User user = (User) auth.getPrincipal();
        if(apparelId == null)
            return "redirect:/error";

        Purchase purchase = purchaseService.purchase(apparelId, user.getUsername());
        metric.update(purchase.getApparel().getPrice());
        return "redirect:/purchases";
    }
}
