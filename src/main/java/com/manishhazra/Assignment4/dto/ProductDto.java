package com.manishhazra.Assignment4.dto;

import com.manishhazra.Assignment4.model.Product;
import com.manishhazra.Assignment4.model.Season;
import com.manishhazra.Assignment4.model.Gender;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ProductDto implements Serializable {
    private UUID id;
    private Gender gender;
    private String brandName;
    private String genericName;
    private Double price;
    private Double discountedPrice;
    private Season season;

    public ProductDto() {

    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public ProductDto(Product apparel) {
        this.id = apparel.getId();
        this.brandName = apparel.getBrandName();
        this.genericName = apparel.getGenericName();
        this.gender = apparel.getGender();
        this.price = apparel.getPrice();
        this.season = apparel.getSeason();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto entity = (ProductDto) o;
        return Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.brandName, entity.brandName) &&
                Objects.equals(this.genericName, entity.genericName) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.season, entity.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, brandName, genericName, price, season);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "gender = " + gender + ", " +
                "brandName = " + brandName + ", " +
                "genericName = " + genericName + ", " +
                "price = " + price + ", " +
                "season = " + season + ")";
    }
}
