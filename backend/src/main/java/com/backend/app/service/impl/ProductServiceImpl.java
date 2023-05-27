package com.backend.app.service.impl;

import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.Product;
import com.backend.app.model.Product;
import com.backend.app.repository.ProductRepository;
import com.backend.app.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService  {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(String id) {
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()){
                return product.get();
            }
            else {
                throw new ResourceNotFoundException("User","username",id);
            }

        }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> addProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }
}
