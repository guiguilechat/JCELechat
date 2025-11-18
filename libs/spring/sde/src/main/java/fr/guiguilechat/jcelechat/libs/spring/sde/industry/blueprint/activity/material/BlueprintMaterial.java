package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.Material;
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

@Entity(name = "SdeIndustryBlueprintMaterial")
@Table(name = "sde_industry_blueprintmaterial",
		indexes = {
				@Index(columnList = "activityId"),
				@Index(columnList = "typeId")
		})
@Getter
@Setter
@NoArgsConstructor
public class BlueprintMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private BlueprintActivity activity;

	/** consumed type id */
	private int typeId;

	private int quantity;

	public static BlueprintMaterial of(Material source, BlueprintActivity act) {
		BlueprintMaterial ret = new BlueprintMaterial();
		ret.setActivity(act);
		ret.setTypeId(source.typeID);
		ret.setQuantity(source.quantity);
		return ret;
	}

}
