package com.backend.app.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(
        name = "Product"
)
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_product_name",columnNames = "product_name")
        }
)
public class Product {
    @Id
    @Column(
            name = "product_id",
            nullable = false,
            columnDefinition = "VARCHAR(100)"
    )
    private String productId;
    @Column(
            name = "product_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String productName;
    @Column(
            name = "product_price",
            nullable = false,
            columnDefinition = "INT"
    )
    private Integer productPrice;
    @Column(
            name = "product_discount",
            columnDefinition = "INT"

    )
    private Integer productDiscount;
    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(
            name = "product_img_url",

            columnDefinition = "TEXT"
    )
    private String productImgURL;
    @Column(
            name = "stock_option",
            columnDefinition = "TEXT"
    )
    private String stockOption;
    @Column(
            name = "buying_price",
            columnDefinition = "INT"
    )
    private Integer buyingPrice;
    @Column(
            name = "SKU",
            columnDefinition = "INT"
    )
    private Integer SKU;
    @OneToMany(
            mappedBy = "product"
    )
    private List<ProductOrder> productOrders= new ArrayList<>();


    public Product() {
    }

    public Product(String productId, String productName, Integer productPrice, Integer productDiscount, String description, String productImgURL, String stockOption, Integer buyingPrice, Integer SKU) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.description = description;
        this.productImgURL = productImgURL;
        this.stockOption = stockOption;
        this.buyingPrice = buyingPrice;
        this.SKU = SKU;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Integer productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImgURL() {
        return productImgURL;
    }

    public void setProductImgURL(String productImgURL) {
        this.productImgURL = productImgURL;
    }

    public String getStockOption() {
        return stockOption;
    }

    public void setStockOption(String stockOption) {
        this.stockOption = stockOption;
    }

    public Integer getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Integer buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Integer getSKU() {
        return SKU;
    }

    public void setSKU(Integer SKU) {
        this.SKU = SKU;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && Objects.equals(productPrice, product.productPrice) && Objects.equals(productDiscount, product.productDiscount) && Objects.equals(description, product.description) && Objects.equals(productImgURL, product.productImgURL) && Objects.equals(stockOption, product.stockOption) && Objects.equals(buyingPrice, product.buyingPrice) && Objects.equals(SKU, product.SKU) && Objects.equals(productOrders, product.productOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productPrice, productDiscount, description, productImgURL, stockOption, buyingPrice, SKU, productOrders);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productDiscount=" + productDiscount +
                ", description='" + description + '\'' +
                ", productImgURL='" + productImgURL + '\'' +
                ", stockOption='" + stockOption + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", SKU=" + SKU +
                ", productOrders=" + productOrders +
                '}';
    }
}
