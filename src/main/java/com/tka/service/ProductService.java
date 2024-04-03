package com.tka.service;

import java.util.List;

import com.tka.model.Product;

public interface ProductService {

	public int addProduct(Product product);
	public Product getProductById(long productId);
	public List<Product>getAllProducts();
	public int deleteProduct(long productId);
	public Product updateProduct(Product product);
	
	public List<Product> filterByPrice(int minPrice, int maxPrice);
	public Product filterByName(String productName);
	public List<Product> sortProduct(String orderType, String field);
	
 }

