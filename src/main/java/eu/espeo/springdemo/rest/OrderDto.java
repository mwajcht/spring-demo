package eu.espeo.springdemo.rest;

import static java.util.stream.Collectors.toList;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import eu.espeo.springdemo.domain.Buyer;
import eu.espeo.springdemo.domain.Order;
import eu.espeo.springdemo.domain.Product;

public record OrderDto(
		UUID businessId,
		ZonedDateTime createTime,
		List<UUID> productIds,
		UUID buyerId) {

	public static OrderDto fromOrder(Order order) {
		return new OrderDto(
				order.getBusinessId(),
				order.getCreateTime(),
				order.getProducts().stream()
						.map(Product::getBusinessId)
						.collect(toList()),
				order.getBuyer().getBusinessId());
	}

	public Order toOrder() {
		return Order.builder()
				.businessId(businessId)
				.createTime(createTime)
				.buyer(Buyer.builder()
						.businessId(buyerId)
						.build())
				.products(productIds.stream()
						.map(productId -> Product.builder()
								.businessId(productId)
								.build())
						.collect(toList()))
				.build();
	}
}
