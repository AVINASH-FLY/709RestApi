package com.tka.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tka.dao.ProductDao;
import com.tka.dao.impl.ProductDaoImpl;
import com.tka.exception.ResourceNotExistException;
import com.tka.exception.SomethingWentWrongException;
import com.tka.model.Product;
import com.tka.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	//ProductDao dao= new ProductDaoImpl();
	@Autowired
	ProductDao dao;
	
	@Override
	public int addProduct(Product product) {
		
	String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	product.setProductId(Long.parseLong(productId));
	
	return dao.addProduct(product);
	}
    
	@Override
	public Product getProductById(long productId) {
	return dao.getProductById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		return dao.getAllProducts();
		
	}
	
	@Override
	public int deleteProduct(long productId) {
		
	    return dao.deleteProduct(productId);
	}

	@Override
	public Product updateProduct(Product product) {
	
		return dao.updateProduct(product);
	}

	@Override
	public List<Product> filterByPrice(int minPrice, int maxPrice) {
		
		return dao.filterByPrice(minPrice,maxPrice);
	}

	@Override
	public Product filterByName(String productName) {
		
		return dao.filterByName(productName);
	}

	@Override
	public List<Product> sortProduct(String orderType, String field) {
		
		return dao.sortPrduct(orderType,field);
	}

	
	
	
	
}
