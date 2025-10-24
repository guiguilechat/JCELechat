package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.BlueprintActivity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeIndustryBlueprintProduct")
@Table(name = "sde_industry_blueprintproduct",
		indexes = {
				@Index(columnList = "activityId"),
				@Index(columnList = "typeId")
		})
@Getter
@Setter
@NoArgsConstructor
public class BlueprintProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private BlueprintActivity activity;

	private BigDecimal probability;

	private int quantity;

	/**
	 * produced type id
	 */
	private int typeId;

	public static BlueprintProduct of(Product source, BlueprintActivity act) {
		BlueprintProduct ret = new BlueprintProduct();
		ret.setActivity(act);
		ret.setProbability(source.probability);
		ret.setTypeId(source.typeID);
		ret.setQuantity(source.quantity);
		return ret;
	}

}
