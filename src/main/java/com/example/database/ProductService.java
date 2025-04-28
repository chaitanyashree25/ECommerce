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

    public void addProducts(List<Product> products){

        productRepository.saveAll(products);

    }

    public List<Product> getAllProducts(){
       return productRepository.findAll();
    }

    public Product getProductById(Long id){
      return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        // First, fetch the existing product by id
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        // Update the fields with new values
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());

        // Save the updated product back to the database
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id){

        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else{
            throw new NoSuchElementException("Product not found");
        }
    }
}
