package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
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
@Entity(name = "SdeBlueprintMaterial")
@Table(name = "sde_blueprint_material", indexes = {
    @Index(columnList = "activity_id,type_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Material implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private BlueprintActivity activity;

	@ManyToOne
	private Type type;

	private int quantity;

	public static Material of(BlueprintActivity bpa, Type type, int quantity) {
		return builder()
				.activity(bpa)
				.type(type)
				.quantity(quantity)
				.build();
	}

}
