package eu.espeo.springdemo.rest;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import eu.espeo.springdemo.domain.Order;

public record FullOrderDto(
		UUID businessId,
		ZonedDateTime createTime,
		List<ProductDto> products,
		BuyerDto buyer,
		BigDecimal total) {

	public static FullOrderDto fromOrder(Order order) {
		return new FullOrderDto(
				order.getBusinessId(),
				order.getCreateTime(),
				order.getProducts().stream()
						.map(ProductDto::fromProduct)
						.collect(toList()),
				BuyerDto.fromBuyer(order.getBuyer()),
				order.getTotal());
	}

	public Order toOrder() {
		return Order.builder()
				.businessId(businessId)
				.createTime(createTime)
				.buyer(buyer.toBuyer())
				.products(products.stream()
						.map(ProductDto::toProduct)
						.collect(toList()))
				.build();
	}
}
