package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.skill;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.Skill;
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

@Entity(name = "SdeIndustryBlueprintSkill")
@Table(name = "sde_industry_blueprintskill",
		indexes = {
				@Index(columnList = "activityId"),
				@Index(columnList = "typeId")
		})
@Getter
@Setter
@NoArgsConstructor
public class BlueprintSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private BlueprintActivity activity;

	private int level;

	/**
	 * skill type id
	 */
	private int typeId;

	public static BlueprintSkill of(Skill source, BlueprintActivity act) {
		BlueprintSkill ret = new BlueprintSkill();
		ret.setActivity(act);
		ret.setLevel(source.level);
		ret.setTypeId(source.typeID);
		return ret;
	}

}
