package fr.guiguilechat.jcelechat.libs.spring.items.effect;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiItemsEffect")
@Table(name = "esi_items_effect")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Effect extends ARemoteFetchedResource<Integer, R_get_dogma_effects_effect_id> {

	/**
	 * isAssistance boolean
	 */
	private boolean assistance;

	/**
	 * description string
	 */
	private String description;

	/**
	 * disallowAuto_repeat boolean
	 */
	private boolean disallowAutoRepeat;

	/**
	 * dischargeAttributeId integer
	 */
	@ManyToOne
	private Attribute dischargeAttribute;

	/**
	 * display_name string
	 */
	private String displayName;

	/**
	 * durationAttributeId integer
	 */
	@ManyToOne
	private Attribute durationAttribute;

	/**
	 * effectCategory integer
	 */
	private int effectCategory;

	/**
	 * electronicChance boolean
	 */
	private boolean electronicChance;

	/**
	 * falloffAttributeId integer
	 */
	@ManyToOne
	private Attribute falloffAttribute;

	/**
	 * iconId integer
	 */
	private int iconId;

	/**
	 * modifiers array
	 */
	@OneToMany(mappedBy = "effect")
	private List<Modifier> modifiers;

	/**
	 * name string
	 */
	private String name;

	/**
	 * is_offensive boolean
	 */
	private boolean offensive;

	/**
	 * postExpression integer
	 */
	private int postExpression;

	/**
	 * preExpression integer
	 */
	private int preExpression;

	/**
	 * published boolean
	 */
	private boolean published;

	/**
	 * rangeAttributeId integer
	 */
	@ManyToOne
	private Attribute rangeAttribute;

	/**
	 * rangeChance boolean
	 */
	private boolean rangeChance;

	/**
	 * trackingSpeedAttributeId integer
	 */
	@ManyToOne
	private Attribute trackingSpeedAttribute;

	/**
	 * is_warpSafe boolean
	 */
	private boolean warpSafe;

	@Override
	public void update(R_get_dogma_effects_effect_id data) {
		setAssistance(data.is_assistance);
		setDescription(data.description);
		setDisallowAutoRepeat(data.disallow_auto_repeat);
		setDisplayName(data.display_name);
		setEffectCategory(data.effect_category);
		setElectronicChance(data.electronic_chance);
		setIconId(data.icon_id);
		setName(data.name);
		setOffensive(data.is_offensive);
		setPostExpression(data.post_expression);
		setPreExpression(data.pre_expression);
		setPublished(data.published);
		setRangeChance(data.range_chance);
		setWarpSafe(data.is_warp_safe);
	}

}
