package com.backend.app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(
        name = "ProductOrder"
)
@Table(
        name = "product_order"
)

public class ProductOrder {
    @EmbeddedId
    private ProductOrderId productOrderId;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(
            name="order_id",
            foreignKey = @ForeignKey(
                    name = "product_order_order_id_fk"
            )
    )
    @JsonIgnore
    private Order order;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(
            name="product_id",
            foreignKey = @ForeignKey(
                    name = "product_order_product_id_fk"
            )
    )
    @JsonIgnore
    private  Product product;
    @Column(
            name = "quantity",
            columnDefinition = "INT"

    )
    private Integer quantity;
    @Column(
            name = "unit_price",
            columnDefinition = "INT"

    )
    private Integer unitPrice;

    public ProductOrder() {

    }

    public ProductOrder(ProductOrderId productOrderId, Order order, Product product) {
        this.productOrderId = productOrderId;
        this.order = order;
        this.product = product;
    }

    public ProductOrder(Order order, Product product, Integer quantity, Integer unitPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public ProductOrder(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public ProductOrderId getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(ProductOrderId productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ProductOrder(ProductOrderId productOrderId, Order order, Product product, Integer quantity, Integer unitPrice) {
        this.productOrderId = productOrderId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return Objects.equals(productOrderId, that.productOrderId) && Objects.equals(order, that.order) && Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity) && Objects.equals(unitPrice, that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productOrderId, order, product, quantity, unitPrice);
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "productOrderId=" + productOrderId +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
