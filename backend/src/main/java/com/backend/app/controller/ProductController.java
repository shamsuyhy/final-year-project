package com.backend.app.controller;

import com.backend.app.model.Product;
import com.backend.app.model.User;
import com.backend.app.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);
    }
    @PostMapping
    public ResponseEntity addProducts(@RequestBody List<Product> products){
        productService.addProducts(products);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("{id}")
    public Product getProductById(@PathVariable(name = "id") String id){
        return productService.getProductById(id);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @DeleteMapping
    public  ResponseEntity deleteAllProducts(){
        productService.deleteAllProducts();
        return new ResponseEntity(HttpStatus.OK);
    }

}
