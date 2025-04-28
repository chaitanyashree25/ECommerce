package com.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(Product product){

        productRepository.save(product);

    }

    public List<Product> getAllProducts(){
       return productRepository.findAll();
    }

    public Product getProductById(Long id){
      return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public void deleteProduct(Long id){

        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else{
            throw new NoSuchElementException("Product not found");
        }
    }
}
