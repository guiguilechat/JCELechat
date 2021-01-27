package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_IDCAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems_cost_indices;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import lombok.Getter;

public class Industry {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Industry.class);

	public final G_IDCAccess con;

	public Industry(G_IDCAccess conn) {
		con = conn;
	}

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, IndustryIndices> systemIndices = ESIStatic.INSTANCE.cache().industry.systems()
	.toMap(r -> r.solar_system_id, IndustryIndices::new);

	/**
	 * get the indices of a system
	 *
	 * @param sysID
	 * @return
	 */
	public IndustryIndices getSystemIndices(int sysID) {
		return getSystemIndices().get(sysID);
	}

	/**
	 * contains the industry indices per activy, as a multiplier of the base cost.
	 * eg a 1% manufacturing system will have its manufacturing set to 0.01
	 *
	 */
	public static class IndustryIndices {
		public float manufacturing, researching_time_efficiency, researching_material_efficiency, copying, invention,
		reaction;

		public IndustryIndices(R_get_industry_systems r) {
			for (R_get_industry_systems_cost_indices t : r.cost_indices) {
				switch (t.activity) {
				case copying:
					copying = t.cost_index;
					break;
				case invention:
					invention = t.cost_index;
					break;
				case manufacturing:
					manufacturing = t.cost_index;
					break;
				case reaction:
					reaction = t.cost_index;
					break;
				case researching_material_efficiency:
					researching_material_efficiency = t.cost_index;
					break;
				case researching_time_efficiency:
					researching_time_efficiency = t.cost_index;
					break;
				default:
					throw new UnsupportedOperationException("can't handle activity " + t.activity);
				}
			}
		}
	}

	/**
	 * all facilities in the game, by ids
	 */
	@Getter(lazy = true)
	private final ObsMapHolder<Long, R_get_industry_facilities> facilities = ESIStatic.INSTANCE.cache().industry
	.facilities().toMap(fac -> fac.facility_id);

}
