package eu.espeo.springdemo.domain;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Buyer {
	private UUID businessId;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String postalCode;
	private String country;
	private Set<Order> orders;
}
