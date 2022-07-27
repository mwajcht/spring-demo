package eu.espeo.springdemo.rest;

import eu.espeo.springdemo.domain.Product;
import eu.espeo.springdemo.domain.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerWebMockTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    @Test
    void shouldSaveAndRetrieveProduct() {
        // given
        var productBusinessId = UUID.randomUUID();
        var productName = "Apple MacBook";
        var price = BigDecimal.valueOf(11499.90);
        var productDto = new ProductDto(productBusinessId, productName, price);
        given(productService.create(any())).willAnswer(answer -> answer.getArgument(0));
        given(productService.findByBusinessId(productBusinessId)).willReturn(Product.builder()
                .businessId(productBusinessId)
                .name(productName)
                .price(price)
                .build());

        // when
        restTemplate.postForObject("http://localhost:" + port + "/products", productDto, ProductDto.class);
        var product = restTemplate.getForObject(
                "http://localhost:" + port + "/products/" + productBusinessId,
                ProductDto.class);

        // then
        then(product).isNotNull();
        then(product.businessId()).isEqualTo(productBusinessId);
        then(product.name()).isEqualTo(productName);
        then(product.price()).isEqualByComparingTo(price);
    }

}
