package fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.modifier;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.IndustryActivity;
import fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.targetfilter.IndustryTargetFilter;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Entity(name = "EveIndustryModifier")
@Table(name = "eve_industry_activitymodifier", indexes = {
		@Index(columnList = "type_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class IndustryModifier implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * types that modifies jobs fields
	 */
	@ManyToOne
	private Type type;

	/** job's activity modified by the type */
	@ManyToOne
	private IndustryActivity activity;

	/**
	 * job's product filter ; null means all.
	 */
	@ManyToOne
	private IndustryTargetFilter productFilter;

	public enum Modifiedfield {
		cost, material, time;
	}

	/**
	 * job's field that is modified
	 */
	@Enumerated(EnumType.STRING)
	private Modifiedfield modifies;

	/**
	 * type's field whose value is applied to modify (mult ?)
	 */
	@ManyToOne
	private Attribute modifyingAttribute;

}
