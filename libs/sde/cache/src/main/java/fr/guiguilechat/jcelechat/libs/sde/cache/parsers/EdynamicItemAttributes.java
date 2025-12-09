package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

/**
 * key is mutaplasmid typeid, value is effect on type and output
 */
public class EdynamicItemAttributes {

	//
	// SDE loading
	//

	public static final IntMapLoader<EdynamicItemAttributes> LOADER = new IntMapLoader<>(
			"dynamicItemAttributes",
			EdynamicItemAttributes.class,
			Set.of("attributeIDs"));

	//
	// file structure
	//

	public static class MinMax {
		public boolean highIsGood;
		public BigDecimal max;
		public BigDecimal min;
	}
	public Map<Integer, MinMax> attributeIDs = new LinkedHashMap<>();

	public static class TransformTypes {
		/** {@link Etypes} */
		public List<Integer> applicableTypes = new ArrayList<>();
		/** {@link Etypes} */
		public int resultingType;
	}
	public List<TransformTypes> inputOutputMapping = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		long morethanOneMapping = loaded.values().stream().filter(att -> att.inputOutputMapping.size() > 1).count();
		System.out.println("with more than one mapping : " + morethanOneMapping);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : attributes=" + first.attributeIDs.size());
	}
}
