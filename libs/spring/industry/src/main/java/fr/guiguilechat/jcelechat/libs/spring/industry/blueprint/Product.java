package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Entity(name = "SdeBlueprintProduct")
@Table(name = "sde_blueprint_product", indexes = {
    @Index(columnList = "	activity_id,type_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private BlueprintActivity activity;

	@ManyToOne
	private Type type;

	private double probability;
	private int quantity;

	public static Product of(BlueprintActivity bpa, Type type, double probability, int quantity) {
		if (type == null) {
			throw new RuntimeException("type null for bp activity " + bpa.getType().name() + " " + bpa.getActivity());
		}
		return builder()
				.activity(bpa)
				.type(type)
				.probability(probability)
				.quantity(quantity)
				.build();
	}

}
