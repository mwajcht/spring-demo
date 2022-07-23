package eu.espeo.springdemo.db;

import static java.util.stream.Collectors.toList;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private UUID businessId;

	private ZonedDateTime createTime;

	@ManyToOne
	@JoinColumn(name = "buyer_id", nullable = false)
	private Buyer buyer;

	@ManyToMany(fetch = javax.persistence.FetchType.EAGER)
	@JoinTable(name = "orders_products",
			joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	@Builder.Default
	private List<Product> products = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Order order = (Order) o;
		return id != null && Objects.equals(id, order.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public eu.espeo.springdemo.domain.Order toOrder() {
		return eu.espeo.springdemo.domain.Order.builder()
				.businessId(businessId)
				.createTime(createTime)
				.buyer(buyer.toBuyer())
				.products(products.stream()
						.map(Product::toProduct)
						.collect(toList()))
				.total(products.stream()
						.map(Product::getPrice)
						.reduce(BigDecimal.ZERO, BigDecimal::add))
				.build();
	}

	public static Order fromOrder(eu.espeo.springdemo.domain.Order order) {
		return Order.builder()
				.businessId(order.getBusinessId())
				.createTime(order.getCreateTime())
				.buyer(eu.espeo.springdemo.db.Buyer.fromBuyer(order.getBuyer()))
				.products(order.getProducts().stream()
						.map(Product::fromProduct)
						.collect(toList()))
				.build();
	}
}
