package com.manishhazra.Assignment4.service;

import com.manishhazra.Assignment4.dto.DiscountDto;
import com.manishhazra.Assignment4.model.Product;
import com.manishhazra.Assignment4.model.Discount;
import com.manishhazra.Assignment4.repository.ProductRepo;
import com.manishhazra.Assignment4.repository.DiscountRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepo dealRepository;
    private final ProductRepo apparelRepository;

    public DiscountServiceImpl(DiscountRepo dealRepository, ProductRepo apparelRepository) {
        this.dealRepository = dealRepository;
        this.apparelRepository = apparelRepository;
    }

    @Override
    public double getDiscountedPrice(UUID apparelId) {

        double percentage = Optional.ofNullable(dealRepository.findByApparel_Id(apparelId))
                .map(deal -> {
                    if (deal.getValidUpto().isBefore(LocalDateTime.now())) {
                        dealRepository.delete(deal);
                        return 0.0;
                    } else if (deal.getValidFrom().isAfter(LocalDateTime.now())) {
                        return 0.0;
                    }
                    return deal.getPercentage();
                }).orElse(0.0);
        Product apparel = apparelRepository.getById(apparelId);
        return apparel.getPrice() * (1 - percentage / 100);
    }

    @Override
    public Discount save(DiscountDto dealDto) {
        if(dealDto.getValidFrom().isEmpty())
            dealDto.setValidFrom(LocalDateTime.now().toString());
        Discount deal = new Discount(apparelRepository.getById(dealDto.getApparelId()),
                dealDto.getPercentage(),
                LocalDateTime.parse(dealDto.getValidFrom()),
                LocalDateTime.parse(dealDto.getValidUpto())
        );
        return dealRepository.save(deal);
    }
}
