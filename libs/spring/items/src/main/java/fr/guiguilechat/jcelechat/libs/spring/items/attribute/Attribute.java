package fr.guiguilechat.jcelechat.libs.spring.items.attribute;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiConnectItemAttribute")
@Table(name = "esi_connect_itemattribute")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attribute extends ARemoteFetchedResource<Integer, R_get_dogma_attributes_attribute_id> {

	/**
	 * default_value number
	 */
	private float defaultValue;

	/**
	 * description string
	 */
	private String description;

	/**
	 * display_name string
	 */
	private String displayName;

	/**
	 * high_is_good boolean
	 */
	private boolean highIsGood;

	/**
	 * icon_id integer
	 */
	private int iconId;

	/**
	 * name string
	 */
	private String name;

	/**
	 * published boolean
	 */
	private boolean published;

	/**
	 * stackable boolean
	 */
	private boolean stackable;

	/**
	 * unit_id integer
	 */
	private int unitId;

	@Override
	public void update(R_get_dogma_attributes_attribute_id data) {
		setDefaultValue(data.default_value);
		setDescription(data.description);
		setDisplayName(data.display_name);
		setHighIsGood(data.high_is_good);
		setIconId(data.icon_id);
		setName(data.name);
		setPublished(data.published);
		setStackable(data.stackable);
		setUnitId(data.unit_id);
	}

}