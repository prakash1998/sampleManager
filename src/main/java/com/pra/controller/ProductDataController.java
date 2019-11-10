package com.pra.controller;

import org.springframework.stereotype.Service;

import com.pra.controller.interfaces.SimpleBaseDataController;
import com.pra.model.Product;
import com.pra.view.ProductDataWindow;

@Service
public class ProductDataController extends SimpleBaseDataController<Product,ProductController , ProductDataWindow>{
	
	
}
