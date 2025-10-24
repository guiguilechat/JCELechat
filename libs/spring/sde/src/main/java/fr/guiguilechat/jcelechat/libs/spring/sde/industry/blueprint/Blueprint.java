package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeIndustryBlueprint")
@Table(name = "sde_industry_blueprint")
@Getter
@Setter
@NoArgsConstructor
public class Blueprint extends SdeEntity<Integer> {

	/** max runs of a copied bpc */
	private int maxProductionLimit;

	@OneToMany(mappedBy = "typeId")
	private List<BlueprintActivity> actvities = new ArrayList<>();

	public void update(Eblueprints source) {
		receivedSource();
		setMaxProductionLimit(source.maxProductionLimit);
	}

}
