package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.impl.ObsMapHolderImpl;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_IDCAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems_cost_indices;

public class Industry {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Industry.class);

	public final G_IDCAccess con;

	private ObsMapHolderImpl<Integer, IndustryIndices> indicesMap = null;

	public Industry(G_IDCAccess conn) {
		con = conn;
	}

	public IndustryIndices getSystemIndices(int sysID) {
		if (indicesMap == null) {
			synchronized (this) {
				if (indicesMap == null) {
					indicesMap = ObsMapHolderImpl.toMap(ESIStatic.INSTANCE.cache.industry.systems(), r -> {
						return r.solar_system_id;
					}, IndustryIndices::new);
				}
			}
		}
		return indicesMap.get(sysID);
	}

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

}
