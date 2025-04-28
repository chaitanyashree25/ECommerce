package com.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product){

    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return null;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return null;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){

    }

}
