package eu.espeo.springdemo.domain;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
	private UUID businessId;
	private String name;
	private BigDecimal price;
	private Order order;
}
