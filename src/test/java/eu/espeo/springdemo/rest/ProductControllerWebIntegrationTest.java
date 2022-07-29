package eu.espeo.springdemo.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerWebIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldSaveAndRetrieveProduct() {
        // given
        var productBusinessId = UUID.randomUUID();
        var productName = "Apple MacBook";
        var price = BigDecimal.valueOf(11499.90);

        // when
        var createResponse = restTemplate
                .postForEntity("http://localhost:" + port + "/products",
                        new ProductDto(productBusinessId, productName, price), ProductDto.class);
        then(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        var product = restTemplate.getForObject(
                "http://localhost:" + port + "/products/" + productBusinessId, ProductDto.class);

        // then
        then(product).isNotNull();
        then(product.businessId()).isEqualTo(productBusinessId);
        then(product.name()).isEqualTo(productName);
        then(product.price()).isEqualByComparingTo(price);
    }
}
