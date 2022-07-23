package eu.espeo.springdemo.rest;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.espeo.springdemo.domain.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/orders", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping
	public List<FullOrderDto> listOrders() {
		return orderService.findAll().stream()
				.map(FullOrderDto::fromOrder)
				.collect(toList());
	}

	@GetMapping(value = "/{orderId}")
	public FullOrderDto getOrder(@PathVariable("orderId") String orderId) {
		return FullOrderDto.fromOrder(orderService.findById(UUID.fromString(orderId)));
	}

	@PostMapping
	public OrderDto create(@RequestBody OrderDto orderDto) {
		return OrderDto.fromOrder(orderService.create(orderDto.toOrder()));
	}

	@DeleteMapping("/{orderId}")
	public void delete(@PathVariable("orderId") String orderId) {
		orderService.delete(UUID.fromString(orderId));
	}
}
