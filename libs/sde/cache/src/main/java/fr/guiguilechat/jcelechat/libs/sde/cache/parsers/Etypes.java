package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Etypes {

	private static final Logger logger = LoggerFactory.getLogger(Etypes.class);

	//
	// SDE loading
	//

	public static final IntMapLoader<Etypes> LOADER = new IntMapLoader<>(
			"types",
			Etypes.class,
			Set.of("groupID"));

	//
	// file structure
	//

	public BigDecimal basePrice;
	public BigDecimal capacity;
	public HashMap<String, String> description = new HashMap<>();
	/** {@link Efactions} */
	public int factionID;
	public int graphicID;
	/** {@link Egroups} */
	public int groupID;
	public int iconID;
	/** {@link EmarketGroups} */
	public int marketGroupID;
	public BigDecimal mass;
	/** {@link EmetaGroups} */
	public int metaGroupID;
	public HashMap<String, String> name = new HashMap<>();
	public int portionSize;
	public boolean published;
	/** {@link Eraces} */
	public int raceID;
	public BigDecimal radius;
	public int soundID;
	public Integer variationParentTypeID;
	public BigDecimal volume;


	public String enName() {
		return name == null ? null : name.get("en");
	}

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	//

	private static final HashMap<Integer, String> ERROR_NAMES = new HashMap<>();

	public static String getName(int typeId) {
		var type = LOADER.yaml().load().get(typeId);
		if (type != null) {
			return type.enName();
		}
		synchronized (ERROR_NAMES) {
			return ERROR_NAMES.computeIfAbsent(typeId, i -> {
				logger.warn("can't load type id " + i, new Exception());
				return "missingType_" + i;
			});
		}
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName() + " description=" + first.enDescription());
	}

}
