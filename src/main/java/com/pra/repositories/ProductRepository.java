package com.pra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pra.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
