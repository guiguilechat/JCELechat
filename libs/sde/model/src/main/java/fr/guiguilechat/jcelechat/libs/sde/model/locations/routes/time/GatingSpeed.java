package fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.time;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AOrbiting;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.AStar.ItemDist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GatingSpeed {

	private final double ws_aups;
	private final int align_s;
	private final double subwarpSpeed_mps;
	private final double gateTime_s;

	public List<ItemDist<GatingPoint>> evaluate(AOrbiting<?> start, AOrbiting<?> end) {
		return new GatingSearch(new GatingPoint.OrbitingPoint(start),
				new GatingPoint.OrbitingPoint(end), ws_aups, align_s, subwarpSpeed_mps, gateTime_s).search();
	}

}
