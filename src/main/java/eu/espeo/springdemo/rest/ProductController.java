package eu.espeo.springdemo.rest;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.espeo.springdemo.domain.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public List<ProductDto> listProducts() {
		return productService.findAll().stream()
				.map(ProductDto::fromProduct)
				.collect(toList());
	}

	@GetMapping(value = "/{productId}")
	public ProductDto getProduct(@PathVariable("productId") String productId) {
		return ProductDto.fromProduct(productService.findByBusinessId(UUID.fromString(productId)));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto create(@RequestBody ProductDto productDto) {
		return ProductDto.fromProduct(productService.create(productDto.toProduct()));
	}

	@DeleteMapping("/{productId}")
	public void delete(@PathVariable("productId") String productId) {
		productService.delete(UUID.fromString(productId));
	}
}
