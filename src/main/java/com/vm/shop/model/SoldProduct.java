package com.vm.shop.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vm.shop.utils.MoneySerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "sold_product")
public class SoldProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sold_product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "price")
    @JsonSerialize(using = MoneySerializer.class)
    private @NotNull BigDecimal soldPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoldProduct that = (SoldProduct) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        return soldPrice != null ? soldPrice.equals(that.soldPrice) : that.soldPrice == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (soldPrice != null ? soldPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SoldProduct{" +
                "id=" + id +
                ", product=" + product +
                ", soldPrice=" + soldPrice +
                '}';
    }
}
