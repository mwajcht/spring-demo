package eu.espeo.springdemo.db;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer,Integer> {
	@Override
	List<Buyer> findAll();

	Optional<Buyer> findByBusinessId(UUID businessId);

	void deleteByBusinessId(UUID businessId);
}
