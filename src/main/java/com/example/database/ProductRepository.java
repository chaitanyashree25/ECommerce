package com.example.database;

import org.springframework.data.jpa.repository.JpaRepository;

/*Product → your entity class.

Long → type of @Id field (your id is a Long).*/

public interface ProductRepository extends JpaRepository<Product, Long> {




}
