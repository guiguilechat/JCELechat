package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.spring.items.effect.Effect;
import fr.guiguilechat.jcelechat.libs.spring.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.items.marketgroup.MarketGroup;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiItemsType")
@Table(name = "esi_items_type", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "group_id"),
    @Index(columnList = "published"),
    @Index(columnList = "market_group_id"),
    @Index(columnList = "name") })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Type extends ARemoteEntity<Integer, R_get_universe_types_type_id> {

	@OneToMany(mappedBy = "type")
	private List<TypeAttribute> attributes = new ArrayList<>();

	/**
	 * dogma_effects array
	 */
	@ElementCollection(fetch = FetchType.LAZY)
	private Map<Effect, Boolean> effectsDefault = new HashMap<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Group group;

	/** data integrated from SDE */
	private float basePrice;

	/**
	 * capacity number
	 */
	private float capacity;

	/**
	 * description string
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
	private TypeDescription description;

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
	@ManyToOne
	private MarketGroup marketGroup;

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
	private float radius;

	/**
	 * when no null, the main typeId of that type's variation
	 */
	private Integer variationTypeId;

	/**
	 * volume number
	 */
	private float volume;

	public String name() {
		if (name != null) {
			return name;
		}
		return "type:" + getId();
	}

	@Override
	public String toString() {
		return name == null ? "type:" + getId() : name + "(" + getId() + ")";
	}

	@Override
	public void update(R_get_universe_types_type_id data) {
		setCapacity(data.capacity);
		setDescription(new TypeDescription(null, this, data.description));
		setGraphicId(data.graphic_id);
		setIconId(data.icon_id);
		setMass(data.mass);
		setName(data.name);
		setPackagedVolume(data.packaged_volume);
		setPortionSize(data.portion_size);
		setPublished(data.published);
		setRadius(data.radius);
		setVolume(data.volume);
	}

}
