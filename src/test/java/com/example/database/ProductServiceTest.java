package com.example.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)  // Enable Mockito in JUnit 5
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository; // Fake ProductRepository

    Product product = new Product(1L,"Ball",1002,"This is a normal blue ball");

    @InjectMocks
    private ProductService productService;  // ProductService with mocked repository injected

    @Test
    public void testAddProduct(){
        productService.addProduct(product);
        verify(productRepository).save(product);  // ✅ Verify that save() was called
    }

    @Test
    public void testGetAllProducts(){
          productService.getAllProducts();
          verify(productRepository).findAll();
    }


    @Test
    public void testGetProductById(){

        // Arrange: mock the repository to return a product when findById is called
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
            productService.getProductById(1L);
        verify(productRepository).findById(1L);
    }

    @Test
    public void testDeleteProduct(){

        Mockito.when(productRepository.existsById(1L)).thenReturn(true);
        productService.deleteProduct(1L);
        verify(productRepository).deleteById(1L);
    }


    @Test
    public void testDeleteWhenNotFound(){

        Mockito.when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () ->{
            productService.deleteProduct(1L);
        });

        // Verify that deleteById was not called
        verify(productRepository, Mockito.never()).deleteById(1L);
    }

    @Test
    public void testUpdateProduct(){

        Product existingProduct = new Product(1L,"Bat",1003,"This is a Baseball bat");

        // Mock the repository to return the existing product when findById is called
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));

        // Create an updated product with the same ID
        Product updatedProduct = new Product(1L,"BaseBallBat",2000,"This is a BaseBall bat");

        // Act: Call the updateProduct method
        productService.updateProduct(1L,updatedProduct);

        // Assert: Verify that save was called with the updated product
        verify(productRepository).save(existingProduct);
    }
}
