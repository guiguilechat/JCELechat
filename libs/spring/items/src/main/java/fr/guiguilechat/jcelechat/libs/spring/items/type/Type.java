package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.items.effect.Effect;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiItemsType")
@Table(name = "esi_items_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Type extends ARemoteFetchedResource<Integer, R_get_universe_types_type_id> {

	@ManyToOne
	private Group group;

	/**
	 * capacity number
	 */
	private float capacity;

	/**
	 * description string
	 */
	private String description;

	/**
	 * dogma_attributes array
	 */
	@ElementCollection
	private Map<Attribute, Float> attributesValues = new HashMap<>();

	/**
	 * dogma_effects array
	 */
	@ElementCollection
	private Map<Effect, Boolean> effectsDefault = new HashMap<>();

	/**
	 * graphic_id integer
	 */
	private int graphicId;

	/**
	 * icon_id integer
	 */
	private int iconId;

	/**
	 * This only exists for types that can be put on the market
	 */
	private int marketGroupId;

	/**
	 * mass number
	 */
	private float mass;

	/**
	 * name string
	 */
	private String name;

	/**
	 * packaged_volume number
	 */
	private float packagedVolume;

	/**
	 * portion_size integer
	 */
	private int portionSize;

	/**
	 * published boolean
	 */
	private boolean published;

	/**
	 * radius number
	 */
	public float radius;

	/**
	 * volume number
	 */
	private float volume;

	@Override
	public void update(R_get_universe_types_type_id data) {
		// TODO Auto-generated method stub

	}

}
