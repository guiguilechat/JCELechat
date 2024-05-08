package fr.guiguilechat.jcelechat.programs.showattributes.lp;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;

public class TypeGrouper {
	CacheStatic access = ESIRawPublic.INSTANCE.cache();

	/**
	 * cached type id 2 semantic id.
	 */
	private HashMap<Integer, Integer> semantics = new HashMap<>();

	public int semantic(int typeid) {
		if (!semantics.containsKey(typeid)) {
			synchronized (semantics) {
				if (!semantics.containsKey(typeid)) {
					R_get_universe_types_type_id type = access.universe.types(typeid).get();
					R_get_universe_types_type_id sem = semantic(type);
					semantics.put(typeid, sem.type_id);
					return sem.type_id;
				}
			}
		}
		return semantics.get(typeid);
	}

	private HashMap<Map<Integer, Double>, Set<R_get_universe_types_type_id>> semantic2Types = new HashMap<>();

	private static final Set<Integer> BPGROUPS = Blueprint.METACAT.groups().stream().map(bp -> bp.getGroupId())
			.collect(Collectors.toSet());

	private static final R_get_universe_types_type_id INVALID_SEMANTIC = new R_get_universe_types_type_id();
	static {
		INVALID_SEMANTIC.name = "no semantic";
	}

	private R_get_universe_types_type_id semantic(R_get_universe_types_type_id type) {
		// replace a bp by its type
		R_get_universe_types_type_id product = type;
		if (BPGROUPS.contains(type.group_id)) {
			fr.guiguilechat.jcelechat.model.sde.industry.Blueprint bp = fr.guiguilechat.jcelechat.model.sde.industry.Blueprint
					.of(type.type_id);
			product = access.universe.types(bp.manufacturing.products.get(0).id).get();
		}
		Map<Integer, Double> atts = new HashMap<>();
		atts.put(0, (double) product.group_id);
		if (product.dogma_attributes == null) {
			atts.put(-1, (double) product.type_id);
		} else {
			for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes att : product.dogma_attributes) {
				atts.put(att.attribute_id, (double) att.value);
			}
		}
		Set<R_get_universe_types_type_id> set = semantic2Types.computeIfAbsent(atts, o -> new LinkedHashSet<>());
		set.add(type);
		return set.iterator().next();
	}

	public Stream<Set<R_get_universe_types_type_id>> streamGroups() {
		return semantic2Types.values().stream();
	}

}
