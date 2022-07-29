package eu.espeo.springdemo.rest;

import eu.espeo.springdemo.db.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class ProductControllerIntegrationTest {

    @Autowired
    private ProductController sut;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveAndRetrieveProduct() {
        // given
        var productBusinessId = UUID.randomUUID();
        var productName = "NewProduct";
        var price = BigDecimal.valueOf(10.5);

        // when
        sut.create(new ProductDto(productBusinessId, productName, price));
        var product = sut.getProduct(productBusinessId.toString());

        // then
        then(product).isNotNull();
        then(product.businessId()).isEqualTo(productBusinessId);
        then(product.name()).isEqualTo(productName);
        then(product.price()).isEqualByComparingTo(price);
    }

    @Test
    @Disabled //will throw an exception on runtime
    void testWrongReturnType() {
        var productBusinessId = UUID.randomUUID();
        var productName = "NewProduct";
        var price = BigDecimal.valueOf(10.5);

        // when
        sut.create(new ProductDto(productBusinessId, productName, price));
        String product = productRepository.findByName(productName);
    }
}
