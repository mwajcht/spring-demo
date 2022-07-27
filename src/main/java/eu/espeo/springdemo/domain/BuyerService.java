package eu.espeo.springdemo.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.espeo.springdemo.db.BuyerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyerService {
	private final BuyerRepository buyerRepository;

	public List<Buyer> findAll() {
		return buyerRepository.findAll().stream()
				.map(eu.espeo.springdemo.db.Buyer::toBuyer)
				.collect(toList());
	}


	public Buyer findByBusinessId(UUID businessId) {
		return buyerRepository.findByBusinessId(businessId)
				.map(eu.espeo.springdemo.db.Buyer::toBuyer)
				.orElseThrow();
	}

	@Transactional
	public Buyer create(Buyer buyer) {
		return buyerRepository
				.save(eu.espeo.springdemo.db.Buyer.fromBuyer(buyer))
				.toBuyer();
	}

	@Transactional
	public void delete(UUID businessId) {
		buyerRepository.deleteByBusinessId(businessId);
	}
}
