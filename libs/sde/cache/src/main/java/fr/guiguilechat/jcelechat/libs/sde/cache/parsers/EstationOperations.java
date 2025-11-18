package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EstationOperations {

	//
	// SDE loading
	//

	public static final IntMapLoader<EstationOperations> LOADER = new IntMapLoader<>(
			"stationOperations",
			EstationOperations.class,
			Set.of("activityID"));

	//
	// file structure
	//

	/** different from id. No idea what it is */
	public int activityID;
	public BigDecimal border;
	public BigDecimal corridor;
	public HashMap<String, String> description = new HashMap<>();
	public BigDecimal fringe;
	public BigDecimal hub;
	public BigDecimal manufacturingFactor;
	public HashMap<String, String> operationName = new HashMap<>();
	public BigDecimal ratio;
	public BigDecimal researchFactor;
	/** {@link EstationServices} */
	public int[] services;
	/** in the files, 2^i => {@link Etypes} . I guess it's for flag enumeration */
	public HashMap<Integer, Integer> stationTypes = new HashMap<>();

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enOperationName() {
		return operationName == null ? null : operationName.get("en");
	}

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out
				.println("first : operationName=" + first.enOperationName() + " description=" + first.enDescription());
	}

}
