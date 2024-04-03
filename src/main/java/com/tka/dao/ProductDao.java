package com.tka.dao;

import java.util.List;

import com.tka.model.Product;

public interface ProductDao {

	public int addProduct(Product product);
	public Product getProductById(long productId);
	public List<Product> getAllProducts();
	public int deleteProduct(long productId);
	public Product updateProduct(Product product);
	
	
	public List<Product> filterByPrice(int minPrice, int maxPrice);
	public Product filterByName(String productName);
	public List<Product> sortPrduct(String orderType, String field);

	
	
	
}
