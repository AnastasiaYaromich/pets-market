package ru.yaromich.pets.market.api;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель позиции в заказе")
public class OrderItemDto {
    @Schema(description = "ID продукта", required = true, example = "1")
    private Long productId;

    @Schema(description = "Название продукта", required = true, maxLength = 255, minLength = 3, example = "Acana")
    private String productTitle;

    @Schema(description = "Цена за одну единицу продукта", required = true, example = "1200.0")
    private BigDecimal pricePerProduct;

    @Schema(description = "Количество продукта в заказе", required = true, example = "2")
    private int quantity;

    @Schema(description = "Общая стоимость заказа", required = true, example = "2400.0")
    private BigDecimal price;


    public OrderItemDto(Long productId,String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.productId = productId;
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

    public void setProductId(Long productId) { this.productId = productId; }

    public Long getProductId() { return productId; }
}
