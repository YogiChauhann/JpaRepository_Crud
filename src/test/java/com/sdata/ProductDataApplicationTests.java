package com.sdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.sdata.JpaRepo.model.Product;
import com.sdata.JpaRepo.repos.ProductRepos;

@SpringBootTest
class ProductDataApplicationTests {

	@Autowired
	ProductRepos repo;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void findBySQLStoreProcedure() {
	 System.out.println(repo.findAllProducts());
	}
	
	@Test
	public void GetAllProductsByPriceSQLStoreProcedure() {
	 System.out.println(repo.findAllProductsByPrice(2000));
	}
	
	@Test
	public void GetAllProductsCountByPriceSQLStoreProcedure() {
	 System.out.println(repo.findAllProductsCountByPrice(1500));
	}
	
	@Test
	@Transactional
	public void testCaching() {
		
		Session session = entityManager.unwrap(Session.class);
		Product product = repo.findById(1).get();
		
		repo.findById(1).get();
		
		session.evict(product);
		
		repo.findById(1).get();
		
		Product product2 = repo.findById(1).get();
		
		session.evict(product2);
		
		repo.findById(1).get();
		
	}

	@Test
	void testGetAllProducts() {

		Iterable<Product> list = repo.findAll();
		list.forEach(p -> System.out.println(p.getName() + p.getDescription() + p.getPrice()));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + list);
	}

	@Test
	public void testCreate() {
		Product product = new Product();
		product.setName("lapy");
		product.setDescription("its product");
		product.setPrice(22000d);
		repo.save(product);

	}

	@Test
	public void testRead() {

		Product pro = repo.findById(1).get();
		assertNotNull(pro);
		assertEquals("MACBOOK", pro.getName());
		System.out.println(">>>>>>>>>>>>> " + pro.getName());

	}

	@Test
	public void testDelete() {
		if (repo.existsById(2)) {
			System.out.println(">>>>>>>>>>deleting product");
			repo.deleteById(2);
		}
		;
	}

	@Test
	public void testCount() {
		System.out.println("Total Count Record >>>>>>>>> = " + repo.count());
	}

	@Test
	public void testFindByName() {
		List<Product> products = repo.findByName("iphone");
		products.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindByNameAndDescription() {
		List<Product> list = repo.findByNameAndDescription("iphone", "its wow");
		list.forEach(p -> System.out.println(">>>>>>>>>>> " + p.getPrice()));
	}

	@Test
	public void testFindByGreaterThan() {
		List<Product> product = repo.findByPriceGreaterThan(1500d);
		product.forEach(p -> System.out.println(p.getName()));

	}

	@Test
	public void testFindByDescriptinContains() {
		List<Product> product = repo.findByDescriptionContains("its wow");
		product.forEach(p -> System.out.println(p.getDescription()));

	}

	@Test
	public void testFindByPriceBetween() {
		List<Product> products = repo.findByPriceBetween(1500d, 3000d);
		products.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindByNameLike() {
		List<Product> products = repo.findByNameLike("%hon%");
		products.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindByIdIn() {
		List<Product> products = repo.findByIdIn(Arrays.asList(1, 2, 3));
		products.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindAllPaging() {
		Pageable pageable = PageRequest.of(0, 3);
		Page<Product> results = repo.findAll(pageable);
		results.forEach(p -> System.out.println(p.getName()));

	}

	@Test
	public void testFindAllSorting() {

		repo.findAll(Sort.by(new Sort.Order(Direction.DESC, "name"), new Sort.Order(null, "price")))
				.forEach(p -> System.out.println(p.getName()));

		/*
		 * repo.findAll(Sort.by("name", "price")).forEach(p ->
		 * System.out.println(p.getName()));
		 */

	}

	@Test
	public void findAllPagingAndSorting() {
		Pageable pageable = PageRequest.of(1, 5, Direction.DESC, "name");
		repo.findAll(pageable).forEach(p -> System.out.println(p.getName()));

	}

}
