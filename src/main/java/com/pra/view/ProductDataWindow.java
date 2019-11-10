package com.pra.view;

import org.springframework.stereotype.Component;

import com.pra.controller.ProductDataController;
import com.pra.model.Product;
import com.pra.view.basewindows.SimpleBaseDataWindow;

@Component
public class ProductDataWindow extends SimpleBaseDataWindow<Product,ProductDataController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Product> getBeanClass() {
		return Product.class;
	}

	@Override
	protected String setTitle() {
		return "Products";
	}

}
