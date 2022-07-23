package eu.espeo.springdemo.db;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
	@Override
	List<Product> findAll();

	Optional<Product> findByBusinessId(UUID businessId);

	void deleteByBusinessId(UUID businessId);
}
