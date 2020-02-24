package com.jkaref.wawi.backend;

import com.jkaref.wawi.api.model.Data;
import com.jkaref.wawi.api.model.GsonUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ProductRepositoryIT {
	final String jsonObject  = "";
	
	@Autowired
	private ProductRepository productRepository;
	
	@After
	public void tearDown() {
		productRepository.deleteAll();
	}
	
	
	@Test
	public void testSetup() {
		assertThat(productRepository).isNotNull();
	}

	@Test
	public void testAddProduct() {

		long countBefore = productRepository.count();
		
		productRepository.save(new Product(jsonObject));
		
		long countAfter = productRepository.count();
		
		assertThat(countAfter).isEqualTo(countBefore + 1);
	}
	
	@Test
	public void testRemoveProduct() {
		
		Product product = productRepository.save(new Product(jsonObject));

		long countBefore = productRepository.count();
		
		productRepository.delete(product);
		
		long countAfter = productRepository.count();
		
		assertThat(countAfter).isEqualTo(countBefore - 1);
	}
	
	@Test
	public void testProductContent() {
		
		Product product = productRepository.save(new Product(jsonObject));
	
		Optional<Product> actual = productRepository.findById(product.id);
				
		assertThat(actual.isPresent()).isTrue();
		
		assertThat(actual.get()).isEqualTo(product);
	}
	

	@Test
	public void testSerializationRoundTrip() {
		Product product = productRepository.save(new Product(jsonObject));

		Optional<Product> actual = productRepository.findById(product.id);

		assertThat(actual.isPresent());

		Data data = GsonUtil.fromJson(product.getJson());
		System.out.println(data);
	}
}
