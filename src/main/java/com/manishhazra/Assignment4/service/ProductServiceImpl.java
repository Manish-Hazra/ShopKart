package com.manishhazra.Assignment4.service;

import com.manishhazra.Assignment4.dto.ProductDto;
import com.manishhazra.Assignment4.model.Product;
import com.manishhazra.Assignment4.model.Preference;
import com.manishhazra.Assignment4.model.Gender;
import com.manishhazra.Assignment4.model.User;
import com.manishhazra.Assignment4.repository.ProductRepo;
import com.manishhazra.Assignment4.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepo repository;
    private final UserRepo userRepository;

    public ProductServiceImpl(ProductRepo repository, UserRepo userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    @Override
    public Product save(ProductDto dto) {
        Product apparel = new Product(dto);
        return repository.save(apparel);
    }

    @Override
    public List<Product> listApparel(String email) {
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        return repository.findAll()
                .stream()
                .filter(apparel -> apparel.getGender() == Gender.OTHERS || apparel.getGender() == user.getSex())
                .filter(apparel -> {
                    if(user.getPreference() == Preference.NEW_ARRIVAL)
                    {
                        return Duration.between(apparel.getCreatedAt().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() < 7;
                    }

                    return apparel.getSeason().getMonths().contains(LocalDate.now().getMonthValue());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> listApparel() {
        return repository.findAll();
    }
}
