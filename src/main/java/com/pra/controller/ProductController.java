package com.pra.controller;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.BaseEntityController;
import com.pra.model.Product;
import com.pra.repositories.ProductRepository;
import com.pra.view.ProductWindow;

@Service
public class ProductController extends BaseEntityController<Product, Integer, ProductRepository, ProductWindow>{
	
	
}
