package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_IDCAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems_cost_indices;

public class Industry {

	public final G_IDCAccess con;

	public Industry(G_IDCAccess conn) {
		con = conn;
	}

	public IndustryIndices getSystemIndices(int sysID) {
		dlIndexes();
		synchronized (systemIndicesCache) {
			return systemIndicesCache.get(sysID);
		}
	}

	private long systemIndicesCacheExpire = 0;

	private Map<Integer, IndustryIndices> systemIndicesCache = new HashMap<>();

	private void dlIndexes() {
		if (System.currentTimeMillis() <= systemIndicesCacheExpire) {
			return;
		}
		synchronized (systemIndicesCache) {
			if (System.currentTimeMillis() <= systemIndicesCacheExpire) {
				return;
			}
			Requested<R_get_industry_systems[]> req = con.get_industry_systems(null);
			R_get_industry_systems[] results = req.getOK();
			systemIndicesCache.clear();
			for (R_get_industry_systems r : results) {
				int sysid = r.solar_system_id;
				IndustryIndices indices = new IndustryIndices();
				for (R_get_industry_systems_cost_indices t : r.cost_indices) {
					switch (t.activity) {
					case copying:
						indices.copying = t.cost_index;
						break;
					case invention:
						indices.invention = t.cost_index;
						break;
					case manufacturing:
						indices.manufacturing = t.cost_index;
						break;
					case reaction:
						indices.reaction = t.cost_index;
						break;
					case researching_material_efficiency:
						indices.researching_material_efficiency = t.cost_index;
						break;
					case researching_time_efficiency:
						indices.researching_time_efficiency = t.cost_index;
						break;
					default:
						throw new UnsupportedOperationException("can't handle activity " + t.activity);
					}
				}
				systemIndicesCache.put(sysid, indices);
			}
			systemIndicesCacheExpire = System.currentTimeMillis() + req.getCacheExpire();
		}
	}

	public static class IndustryIndices {
		public float manufacturing, researching_time_efficiency, researching_material_efficiency, copying, invention,
		reaction;
	}

}
