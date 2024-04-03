package com.tka.dao.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tka.dao.ProductDao;
import com.tka.exception.ResourceAlreadyExistException;
import com.tka.exception.ResourceNotExistException;
import com.tka.exception.SomethingWentWrongException;
import com.tka.model.Product;

@Component
public class ProductDaoImpl implements ProductDao {

	List<Product> list=new ArrayList<>();
	
	public ProductDaoImpl() {
		list.add(new Product(1,"xyz",10,2500,"2020-02-22","2024-02-22"));
		list.add(new Product(2,"ABC",10,5000,"2020-02-22","2024-02-22"));
		list.add(new Product(3,"PQR",10,2500,"2020-02-22","2024-02-22"));
	}
	
	@Override
	public int addProduct(Product product) {
		
		try {
			for (Product listproduct : list) {
			if(listproduct.getProductName().equalsIgnoreCase(product.getProductName())) {
			throw new ResourceAlreadyExistException("Product already Exist= "+product.getProductName());
			}
			}
			list.add(product);
			return 1;
			
	       }catch (Exception e) {
		    e.printStackTrace();
		    throw new SomethingWentWrongException("something went wrong while retrive product");
		   }
    }
	@Override
	public Product getProductById(long productId) {
		//public Product getProductById(long productId);
		try {
		for (Product product : list) {
			if(product.getProductId()==productId) {
			return product;
		}
		}
		} catch (Exception e) {
		e.printStackTrace();
		throw new SomethingWentWrongException("something went wrong while retrive product");
		}
	    throw new ResourceNotExistException("product not found with id: "+productId);
	}

	@Override
	public List<Product> getAllProducts() {
		
	return list;
	}
   
   
	@Override
	public int deleteProduct(long productId) {
		
        try {
			if(!list.isEmpty()) {
			
		boolean isDeleted=list.removeIf(product -> product.getProductId()==productId);
			if(isDeleted)
		    return 1;
		
				//if(product.getProductId()==productId) {
				//	list.remove(product);
				//	return 1;
               	//	}
		}else {
			throw new ResourceNotExistException("product not exist to be delete, list is empty");
		}
        }	
		catch (ResourceNotExistException e) {
			throw new ResourceNotExistException("product not exist to be delete, list is empty");
		}
        catch (Exception e) {
			throw new SomethingWentWrongException("something went wrong while deleting product");
		}
		throw new ResourceNotExistException("product not exist to be delete, id: "+productId);
        
}

	@Override
	public Product updateProduct(Product product) {
	
		try {
			    
			 /*   boolean status= list.stream()
			    .filter(listProduct ->listProduct.getProductId()==product.getProductId());
			    if(status) {
			    	list.stream()
			    .peek(listProduct ->list.set(list.indexOf(listProduct), product))
			    .orElseThrow(()->new ResourceNotExistException("product not found with id: "+product.getProductId()));
	            
			    return product;
			    } */   //Not completed this code but this is the alternate logic 
			
			for (Product existingProduct : list) {
				if(existingProduct.getProductId()==product.getProductId()) {
					
					//existingProduct.setProductId(product.getProductId());
					//existingProduct.setProductName(product.getProductName());
					//existingProduct.setProductQty(product.getProductQty());
					//existingProduct.setProductPrice(product.getProductPrice());
					//existingProduct.setMfgDate(product.getMfgDate());
					//existingProduct.setExpDate(product.getExpDate());
					
				list.set(list.indexOf(existingProduct), product);//Alternate for above setters
				return product;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("something went wrong while updating product");
			}
		    throw new ResourceNotExistException("product not found with id: "+product.getProductId());
		
		}

	@Override
	public List<Product> filterByPrice(int minPrice, int maxPrice) {
		
		return (List<Product>) list.stream()
		.filter(product -> product.getProductPrice() >= minPrice && product.getProductPrice() <= maxPrice)
        .collect(Collectors.toList());
	}

	@Override
	public Product filterByName(String productName) {
		if(!list.isEmpty()) {
			try {
				for (Product product : list) {
					if(product.getProductName().equalsIgnoreCase(productName)) {
						return product;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new SomethingWentWrongException("something went wrong while getting product: "+productName);
			}
			}else {
				throw new ResourceNotExistException("Product Not Exist with Name: "+productName);
			}
		throw new ResourceNotExistException("Product Not Exist with Name: "+productName);
		
	}

	@Override
	public List<Product> sortPrduct(String orderType, String field) {
		if(!list.isEmpty()) {
			Comparator<Product>comparator=null;
			switch (field) {
			
			case "id":{
				comparator = Comparator.comparing(Product::getProductId);
				break;
			}
			case "name":{
				comparator = Comparator.comparing(Product::getProductName);
				break;
			}
			case "price":{
				comparator = Comparator.comparing(Product::getProductPrice);
				break;
			}
			default:
				break;
			}
			if(orderType.equals("desc")) {
				comparator = comparator.reversed();
			}
			return list.stream().sorted(comparator).collect(Collectors.toList());
			
		}else {
			throw new ResourceNotExistException("List Is Empty");
		}
	}
}
	
