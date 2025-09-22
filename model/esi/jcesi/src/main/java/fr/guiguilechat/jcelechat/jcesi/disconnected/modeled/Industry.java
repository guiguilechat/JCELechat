package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_IDCAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems_cost_indices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import lombok.Getter;

public class Industry {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Industry.class);

	public final G_IDCAccess con;

	public Industry(G_IDCAccess conn) {
		con = conn;
	}

	@Getter(lazy = true)
	private final MapHolder<Integer, IndustryIndices> systemIndices = ESIRawPublic.INSTANCE.cache().industry.systems()
	.toMap(r -> r.solar_system_id, IndustryIndices::new);

	/**
	 * get the indices of a system
	 *
	 * @param sysID
	 * @return
	 */
	public IndustryIndices getSystemIndices(int sysID) {
		return getSystemIndices().get().get(sysID);
	}

	/**
	 * contains the industry indices per activity, as a multiplier of the base
	 * cost. eg a 1% manufacturing system will have its manufacturing set to 0.01
	 *
	 */
	public static class IndustryIndices {
		public float manufacturing, researching_time_efficiency, researching_material_efficiency, copying, invention,
		reaction;

		public IndustryIndices(R_get_industry_systems other) {
			for (R_get_industry_systems_cost_indices index : other.cost_indices) {
				switch (index.activity) {
				case copying:
					copying = index.cost_index;
					break;
				case invention:
					invention = index.cost_index;
					break;
				case manufacturing:
					manufacturing = index.cost_index;
					break;
				case reaction:
					reaction = index.cost_index;
					break;
				case researching_material_efficiency:
					researching_material_efficiency = index.cost_index;
					break;
				case researching_time_efficiency:
					researching_time_efficiency = index.cost_index;
					break;
				default:
					throw new UnsupportedOperationException("can't handle activity " + index.activity);
				}
			}
		}
	}

	/**
	 * all facilities in the game, by ids
	 */
	@Getter(lazy = true)
	private final MapHolder<Long, R_get_industry_facilities> facilities = ESIRawPublic.INSTANCE.cache().industry
	.facilities().toMap(fac -> fac.facility_id);

	/**
	 * all HS industry indices, by solar system id
	 */
	@Getter(lazy = true)
	private final MapHolder<R_get_universe_systems_system_id, IndustryIndices> hSIndices = ESIRawPublic.INSTANCE
	.cache().industry.systems()
	.follow(this::prefetchSystems)
	.filter(this::isHS)
	    .toMap(r -> ESIRawPublic.INSTANCE.cache().universe.systems(r.solar_system_id).get(), IndustryIndices::new);

	protected void prefetchSystems(List<R_get_industry_systems> l) {
		for (R_get_industry_systems is : l) {
			ESIRawPublic.INSTANCE.cache().universe.systems(is.solar_system_id);
		}
	}

	protected boolean isHS(R_get_industry_systems sys) {
		return ESIRawPublic.INSTANCE.cache().universe.systems(sys.solar_system_id).get().security_status > 0.45f;
	}

	/**
	 * list of manufacturing indexes of systems that are in HS, sorted by manuf
	 * index inc.
	 */
	@Getter(lazy = true)
	private final ListHolder<Double> hsManufIndices = getHSIndices().values()
	.toList(l -> l.stream().mapToDouble(ii -> ii.manufacturing).sorted().boxed().toList());

	private final Map<Integer, DoubleHolder> cached_hsmanuf_centiles = Collections
			.synchronizedMap(new HashMap<Integer, DoubleHolder>());

	/**
	 *
	 * @param centile
	 * @return the cached HS centile holder for the manufacturing indexes. value
	 *         for 0 should be min, value for 100 should be max.
	 */
	public DoubleHolder getHSManufCentile(int centile) {
		return cached_hsmanuf_centiles.computeIfAbsent(centile,
				_ -> getHsManufIndices().mapDouble(l -> l.isEmpty() ? -1 : l.get(indexOfCentile(centile, l.size()))));
	}

	protected static int indexOfCentile(int centile, int nb) {
		if (centile < 0) {
			return 0;
		}
		if (centile > 100) {
			return nb - 1;
		}
		return (int) Math.floor(centile * (nb - 1) / 100);
	}
}
