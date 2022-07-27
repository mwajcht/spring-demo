package eu.espeo.springdemo.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.espeo.springdemo.db.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll().stream()
				.map(eu.espeo.springdemo.db.Product::toProduct)
				.collect(toList());
	}


	public Product findByBusinessId(UUID businessId) {
		return productRepository.findByBusinessId(businessId)
				.map(eu.espeo.springdemo.db.Product::toProduct)
				.orElseThrow();
	}

	@Transactional
	public Product create(Product product) {
		return productRepository
				.save(eu.espeo.springdemo.db.Product.fromProduct(product))
				.toProduct();
	}

	@Transactional
	public void delete(UUID businessId) {
		productRepository.deleteByBusinessId(businessId);
	}
}
