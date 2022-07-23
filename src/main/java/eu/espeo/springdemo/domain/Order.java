package eu.espeo.springdemo.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
	private UUID businessId;
	private ZonedDateTime createTime;
	private Buyer buyer;
	private List<Product> products;
	private BigDecimal total;
}
