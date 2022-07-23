package eu.espeo.springdemo.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.espeo.springdemo.db.BuyerRepository;
import eu.espeo.springdemo.db.OrderRepository;
import eu.espeo.springdemo.db.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final BuyerRepository buyerRepository;
	private final ProductRepository productRepository;

	public List<Order> findAll() {
		return orderRepository.findAll().stream()
				.map(eu.espeo.springdemo.db.Order::toOrder)
				.collect(toList());
	}


	public Order findById(UUID businessId) {
		return orderRepository.findByBusinessId(businessId).toOrder();
	}

	@Transactional
	public Order create(Order order) {
		var orderToBeSaved = eu.espeo.springdemo.db.Order.fromOrder(order);
		orderToBeSaved.setBuyer(buyerRepository.findByBusinessId(order.getBuyer().getBusinessId()).orElseThrow());
		orderToBeSaved.setProducts(order.getProducts().stream()
				.map(product -> productRepository.findByBusinessId(product.getBusinessId())
						.orElseThrow())
				.collect(toList()));
		return orderRepository.save(orderToBeSaved).toOrder();
	}

	@Transactional
	public void delete(UUID businessId) {
		orderRepository.deleteByBusinessId(businessId);
	}
}
