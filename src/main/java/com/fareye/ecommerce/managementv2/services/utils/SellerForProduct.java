package com.fareye.ecommerce.managementv2.services.utils;

public class SellerForProduct {
    private String name;

    private Integer inStock;

    private Double price;

    private Long id;

    public SellerForProduct() {
    }

    public SellerForProduct(String name, Integer inStock, Double price, Long id) {
        this.name = name;
        this.inStock = inStock;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SellerForProduct{" +
                "name='" + name + '\'' +
                ", inStock=" + inStock +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
