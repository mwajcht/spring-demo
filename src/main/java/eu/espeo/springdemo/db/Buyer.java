package eu.espeo.springdemo.db;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Buyers")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Buyer {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private UUID businessId;

	private String firstName;

	private String lastName;

	private String street;

	private String city;

	private String postalCode;

	private String country;

	@OneToMany(cascade = ALL, mappedBy = "buyer")
	@ToString.Exclude
	private Set<Order> orders;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Buyer buyer = (Buyer) o;
		return id != null && Objects.equals(id, buyer.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public eu.espeo.springdemo.domain.Buyer toBuyer() {
		return eu.espeo.springdemo.domain.Buyer.builder()
				.businessId(businessId)
				.firstName(firstName)
				.lastName(lastName)
				.street(street)
				.city(city)
				.postalCode(postalCode)
				.country(country)
				.build();
	}

	public static Buyer fromBuyer(eu.espeo.springdemo.domain.Buyer buyer) {
		return Buyer.builder()
				.businessId(buyer.getBusinessId())
				.firstName(buyer.getFirstName())
				.lastName(buyer.getLastName())
				.street(buyer.getStreet())
				.city(buyer.getCity())
				.postalCode(buyer.getPostalCode())
				.country(buyer.getCountry())
				.build();
	}
}
