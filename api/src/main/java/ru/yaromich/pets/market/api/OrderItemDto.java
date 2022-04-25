package ru.yaromich.pets.market.api;
import java.math.BigDecimal;

public class OrderItemDto {
    private String productTitle;
    private BigDecimal pricePerProduct;
    private int quantity;
    private BigDecimal price;


    public OrderItemDto(String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public OrderItemDto() {
    }

    public void setProductTitle(String productTitle) { this.productTitle = productTitle; }

    public String getProductTitle() { return productTitle; }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
