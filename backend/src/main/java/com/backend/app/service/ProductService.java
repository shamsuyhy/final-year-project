package com.backend.app.service;

import com.backend.app.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(String id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
    List<Product> addProducts(List<Product>products);
    void deleteProduct(Product product);
    void deleteProductById(String id);


}
