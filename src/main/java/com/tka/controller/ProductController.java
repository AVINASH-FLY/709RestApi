package com.tka.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tka.service.ProductService;
import com.tka.exception.ResourceAlreadyExistException;
import com.tka.exception.ResourceNotExistException;
import com.tka.model.Product;
import com.tka.service.impl.*;

@RestController
public class ProductController {

	//ProductService service=new ProductServiceImpl();
	@Autowired
	ProductService service;
	
@PostMapping("/addproduct")
public String addProduct(@RequestBody @Valid Product product) {
		
	
	int status=service.addProduct(product);
	
	return "Added successfully";
	
}
@GetMapping("getProductById/{id}")
public ResponseEntity<Product> getProductById(@PathVariable("id") long productId) {
	
	Product product= service.getProductById(productId);//calling service layer to get product
	
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}

@GetMapping("getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		
	List<Product>productList=service.getAllProducts();
	if(!productList.isEmpty()) {
	return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}else {
		throw new ResourceNotExistException("Product Not Exist, List is Empty");
	}
 }

@DeleteMapping("/deleteProductById") 
	public ResponseEntity<String> deleteProduct(@RequestParam long productId){
		
    int status= service.deleteProduct(productId);
    //return ResponseEntity.ok("Deleted");
	return new ResponseEntity<String>("Deleted",HttpStatus.MOVED_PERMANENTLY);
	}


@PutMapping("updateProduct")
public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
	

	Product updatedProduct= service.updateProduct(product);//calling service layer
	
	
	//return new ResponseEntity<Product>(product,HttpStatus.OK);
	return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
	//return null;
}

@GetMapping("/filterProductByPriceRange")
public ResponseEntity<List<Product>> filterByPrice(@RequestParam int minPrice, @RequestParam int maxPrice){
	
	List<Product> filteredProduct=service.filterByPrice(minPrice,maxPrice);
	
	if(!filteredProduct.isEmpty()) {
		return new ResponseEntity<List<Product>>(filteredProduct,HttpStatus.OK);
		}else {
			throw new ResourceNotExistException("Product Not Exist, List is Empty");
		}
	
}
@GetMapping("/filterByName/{productName}")
public ResponseEntity<Product> filterByName(@PathVariable String productName){
	Product product=service.filterByName(productName);
	
	return ResponseEntity.ok(product);
		
}
@GetMapping("/sort")
public ResponseEntity<List<Product>> sortProducts(@RequestParam String orderType,@RequestParam String field ){
	List<Product>product=service.sortProduct(orderType,field);
	return ResponseEntity.ok(product);

}
}

