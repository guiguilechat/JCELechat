package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ActivityDetails;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product.BlueprintProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.skill.BlueprintSkill;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeIndustryBlueprintActivity")
@Table(name = "sde_industry_blueprintactivity")
@Getter
@Setter
@NoArgsConstructor
public class BlueprintActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ActivityType activityType;

	private int time;

	/** blueprint/type id this activity applies to */
	private int typeId;

	@OneToMany(mappedBy = "activity")
	private List<BlueprintMaterial> materials = new ArrayList<>();

	@OneToMany(mappedBy = "activity")
	private List<BlueprintProduct> products = new ArrayList<>();

	@OneToMany(mappedBy = "activity")
	private List<BlueprintSkill> skills = new ArrayList<>();

	public static BlueprintActivity of(ActivityDetails act, int bpId, ActivityType at) {
		BlueprintActivity ret = new BlueprintActivity();
		ret.setActivityType(at);
		ret.setTime(act.time);
		ret.setTypeId(bpId);
		return ret;
	}

}
