package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.AStar.ItemDist;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.time.GatingPoint;

public class ShowTimingRoutes {

	public static void main(String[] args) {

		// 60008494 Amarr VIII
		// 60003760 Jita IV 4
		// 60003469 Jita V
		Station start = Station.CACHE.of(60003469);
		Station end = Station.CACHE.of(60008494);

		List<ItemDist<GatingPoint>> route = Route.cruiserSpeed().evaluate(start, end);
		System.out.println("from " + start.enName() + " to " + end.enName() + " : jumps=" + route.size() + " time="
				+ route.get(route.size() - 1).distancefromStart());
		for (ItemDist<GatingPoint> o : route) {
			System.out.println(" " + o.item().enName());
		}

	}

}
