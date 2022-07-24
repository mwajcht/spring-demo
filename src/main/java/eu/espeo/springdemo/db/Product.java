package eu.espeo.springdemo.db;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
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
@Table(name = "Products")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private UUID businessId;

	private String name;

	private BigDecimal price;

	@ManyToMany(mappedBy = "products")
	@ToString.Exclude
	@Builder.Default
	private List<Order> orders = new java.util.ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Product product = (Product) o;
		return id != null && Objects.equals(id, product.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public eu.espeo.springdemo.domain.Product toProduct() {
		return eu.espeo.springdemo.domain.Product.builder()
				.businessId(businessId)
				.name(name)
				.price(price)
				.build();
	}

	public static Product fromProduct(eu.espeo.springdemo.domain.Product product) {
		return Product.builder()
				.businessId(product.getBusinessId())
				.name(product.getName())
				.price(product.getPrice())
				.build();
	}
}
