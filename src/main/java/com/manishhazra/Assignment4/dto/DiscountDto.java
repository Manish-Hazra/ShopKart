package com.manishhazra.Assignment4.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class DiscountDto implements Serializable {
    private UUID apparelId;
    private Double percentage;
    private String validFrom;
    private String validUpto;

    public DiscountDto(){}

    public DiscountDto(UUID apparelId, Double percentage, String validFrom, String validUpto) {
        this.apparelId = apparelId;
        this.percentage = percentage;
        this.validFrom = validFrom;
        this.validUpto = validUpto;
    }

    public UUID getApparelId() {
        return apparelId;
    }

    public void setApparelId(UUID apparelId) {
        this.apparelId = apparelId;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidUpto() {
        return validUpto;
    }


    public void setValidUpto(String validUpto) {
        this.validUpto = validUpto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountDto entity = (DiscountDto) o;
        return Objects.equals(this.apparelId, entity.apparelId) &&
                Objects.equals(this.percentage, entity.percentage) &&
                Objects.equals(this.validFrom, entity.validFrom) &&
                Objects.equals(this.validUpto, entity.validUpto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apparelId, percentage, validFrom, validUpto);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "apparelId = " + apparelId + ", " +
                "percentage = " + percentage + ", " +
                "validFrom = " + validFrom + ", " +
                "validUpto = " + validUpto + ")";
    }
}
