package com.sdata.JpaRepo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sdata.JpaRepo.model.Product;

public interface ProductRepos extends PagingAndSortingRepository<Product, Integer> {

	List<Product> findByName(String name);
	List<Product> findByNameAndDescription(String name, String desc);
	List<Product> findByPriceGreaterThan(Double price);
	List<Product> findByDescriptionContains(String arg);
	List<Product> findByPriceBetween(Double price, Double price2);
	List<Product> findByNameLike(String string);
	List<Product> findByIdIn(List<Integer> Ids);
	
	@Query(value = "call GetAllProducts()", nativeQuery = true)
	List<Product> findAllProducts();
	
	@Query(value = "call GetAllProductsByPrice (:price_in)", nativeQuery = true)
	List<Product> findAllProductsByPrice(double price_in);
	
	@Query(value = "call GetAllProductsCountByPrice (:price_in)", nativeQuery = true)
	int findAllProductsCountByPrice(double price_in);

}
