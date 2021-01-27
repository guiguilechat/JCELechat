package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.time.LocalDateTime;
import java.util.Arrays;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_attributes;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsBoolHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsIntHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class Attributes {

	@Getter
	@Accessors(fluent = true)
	private final ESIAccount con;

	/**
	 * the actual data of attributes
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsObjHolder<R_get_characters_character_id_attributes> values = con.raw.cache().characters
	.attributes(con.characterId());

	/**
	 * the value of the charisma attribute
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder charisma = values().mapInt(att -> att.charisma);

	/**
	 * the value of the intelligence attribute
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder intelligence = values().mapInt(att -> att.intelligence);

	/**
	 * the value of the memory attribute
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder memory = values().mapInt(att -> att.memory);

	/**
	 * the value of the perception attribute
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder perception = values().mapInt(att -> att.perception);

	/**
	 * the value of the willpower attribute
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder willpower = values().mapInt(att -> att.willpower);

	/**
	 * the int list of the attributes values, sorted increasing.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsListHolder<Integer> sorted = values()
	.toList(att -> Arrays.asList(att.charisma, att.intelligence, att.memory, att.perception, att.willpower))
	.sorted(Integer::compareTo);

	/**
	 * the highest value of the attributes.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder highest = sorted().mapInt(li -> li.get(4));

	/**
	 * the lowest value of the attributes.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder lowest = sorted().mapInt(li -> li.get(0));

	/**
	 * the sum of the attributes values
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder sum = values()
	.mapInt(li -> li.charisma + li.intelligence + li.memory + li.perception + li.willpower);;

	/**
	 * true if sum of attributes is more than 124 (that is the default
	 * value).<br />
	 * base is 17*5 = 85 attributes, +14 to remap, +5*5 from implants
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsBoolHolder isAccelerated = sum().test(i -> i > 124);

	/**
	 * available bonus remaps
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder bonusRemaps = values().mapInt(att -> att.bonus_remaps);

	/**
	 * local date time for the last remap
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsObjHolder<LocalDateTime> lastRemap = values()
	.map(att -> ESITools.convertLocalDateTime(att.last_remap_date));

	/**
	 * local date time for remap cooldown
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsObjHolder<LocalDateTime> remapCoolDown = values()
	.map(att -> ESITools.convertLocalDateTime(att.accrued_remap_cooldown_date));

	/**
	 * convert the attribute ID to the actual attribute value
	 *
	 * @param attID
	 *          the id of the required attribute, typically used in skills as
	 *          primary/secondary
	 * @param attributes
	 *          character attributes
	 * @return the required attribute value
	 */
	public static int getAttribute(int attID, R_get_characters_character_id_attributes attributes) {
		switch (attID) {
		case 164:
			return attributes.charisma;
		case 165:
			return attributes.intelligence;
		case 166:
			return attributes.memory;
		case 167:
			return attributes.perception;
		case 168:
			return attributes.willpower;
		default:
			return 0;
		}
	}

	/**
	 * convert the attribute ID to the actual attribute value
	 *
	 * @param attID
	 *          the id of the required attribute, typically used in skills as
	 *          primary/secondary
	 * @return the required attribute value
	 */
	public int getAttribute(int attID) {
		return getAttribute(attID, values().get());
	}

}
