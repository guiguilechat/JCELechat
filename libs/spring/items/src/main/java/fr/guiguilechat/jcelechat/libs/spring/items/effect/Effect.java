package fr.guiguilechat.jcelechat.libs.spring.items.effect;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_effects_effect_id_modifiers;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiConnectItemEffect")
@Table(name = "esi_connect_itemeffect")
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
	private int dischargeAttributeId;

	/**
	 * display_name string
	 */
	private String displayName;

	/**
	 * durationAttributeId integer
	 */
	private int durationAttributeId;

	/**
	 * effectCategory integer
	 */
	private int effectCategory;

	/**
	 * effectId integer
	 */
	private int effectId;

	/**
	 * electronicChance boolean
	 */
	private boolean electronicChance;

	/**
	 * falloffAttributeId integer
	 */
	private int falloffAttributeId;

	/**
	 * iconId integer
	 */
	private int iconId;

	/**
	 * modifiers array
	 */
	@OneToMany
	private List<get_dogma_effects_effect_id_modifiers> modifiers;

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
	private int rangeAttributeId;

	/**
	 * rangeChance boolean
	 */
	private boolean rangeChance;

	/**
	 * trackingSpeedAttributeId integer
	 */
	private int trackingSpeedAttributeId;

	/**
	 * is_warpSafe boolean
	 */
	private boolean warpSafe;

	@Override
	public void update(R_get_dogma_effects_effect_id data) {
		// TODO Auto-generated method stub

	}

}
