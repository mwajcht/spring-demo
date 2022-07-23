package eu.espeo.springdemo.db;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

	@Override
	List<Order> findAll();

	Order findByBusinessId(UUID businessId);

	void deleteByBusinessId(UUID businessId);
}
