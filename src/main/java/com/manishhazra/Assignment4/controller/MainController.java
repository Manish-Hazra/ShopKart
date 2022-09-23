package com.manishhazra.Assignment4.controller;

import com.manishhazra.Assignment4.dto.ProductDto;
import com.manishhazra.Assignment4.metric.MVCalc;
import com.manishhazra.Assignment4.model.Product;
import com.manishhazra.Assignment4.model.Purchase;
import com.manishhazra.Assignment4.service.ProductService;
import com.manishhazra.Assignment4.service.DiscountService;
import com.manishhazra.Assignment4.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Controller
public class MainController {
    private final ProductService apparelService;
    private final DiscountService dealService;
    private final UserService userService;

    public MainController(ProductService apparelService, DiscountService dealService, UserService userService) {
        this.apparelService = apparelService;
        this.dealService = dealService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String q, Authentication auth, Model model, HttpSession session) {
        MVCalc metric = (MVCalc) session.getAttribute("metric");
        User user = (User) auth.getPrincipal();

        if (metric == null) {
            metric = new MVCalc();
            userService.getPurchases(user.getUsername()).stream().map(Purchase::getApparel).mapToDouble(Product::getPrice).forEach(metric::update);
            session.setAttribute("metric", metric);
        }

        if (q == null || q.isEmpty()) {

            List<ProductDto> apparelList = apparelService.listApparel(user.getUsername()).stream().map(ProductDto::new)
                    .peek(apparelDto -> apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId())))
                    .collect(Collectors.toList());
            model.addAttribute("apparelList", sortAccordingToMetric(apparelList, metric));
        } else {
            List<ProductDto> apparelList = apparelService.listApparel()
                    .stream()
                    .filter(apparel -> apparel.getBrandName().toLowerCase(Locale.ROOT).startsWith(
                            q.toLowerCase(Locale.ROOT)
                    ) || apparel.getGenericName().toLowerCase(Locale.ROOT).startsWith(q.toLowerCase(Locale.ROOT)))
                    .map(ProductDto::new)
                    .peek(apparelDto -> apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId())))
                    .collect(Collectors.toList());
            model.addAttribute("apparelList", sortAccordingToMetric(apparelList, metric));
        }
        return "index";
    }

    private List<ProductDto> sortAccordingToMetric(List<ProductDto> apparels, MVCalc metric) {

        apparels.sort((a, b) -> {
            double left = metric.getMean() - metric.getStandardDeviation();
            double right = metric.getMean() + metric.getStandardDeviation();
            if (a.getPrice() >= left && a.getPrice() <= right && b.getPrice() >= left && b.getPrice() <= right) {
                return Double.compare(a.getPrice(), b.getPrice());
            } else if (a.getPrice() >= left && a.getPrice() <= right) {
                return -1;
            } else if (b.getPrice() >= left && b.getPrice() <= right) {
                return 1;
            } else {
                return Double.compare(Math.abs(a.getPrice() - metric.getMean()), Math.abs(b.getPrice() - metric.getMean()));
            }
        });

        return apparels;
    }
}
