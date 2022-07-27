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

import eu.espeo.springdemo.domain.BuyerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/buyers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BuyerController {

	private final BuyerService buyerService;

	@GetMapping
	public List<BuyerDto> listBuyers() {
		return buyerService.findAll().stream()
				.map(BuyerDto::fromBuyer)
				.collect(toList());
	}

	@GetMapping(value = "/{buyerId}")
	public BuyerDto getBuyer(@PathVariable("buyerId") String buyerId) {
		return BuyerDto.fromBuyer(buyerService.findByBusinessId(UUID.fromString(buyerId)));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public BuyerDto create(@RequestBody BuyerDto buyerDto) {
		return BuyerDto.fromBuyer(buyerService.create(buyerDto.toBuyer()));
	}

	@DeleteMapping("/{buyerId}")
	public void delete(@PathVariable("buyerId") String buyerId) {
		buyerService.delete(UUID.fromString(buyerId));
	}
}
