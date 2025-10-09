package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.Dijkstra.ItemDist;

public class RoutesMain {

	public static void main(String[] args) {

		SolarSystem jita = SolarSystem.CACHE.of("Jita");
		SolarSystem amarr = SolarSystem.CACHE.of("Amarr");
		List<ItemDist<SolarSystem>> safeRoute;
		List<ItemDist<SolarSystem>> shortRoute;

		safeRoute = Route.safer()
				.from("jita")
				.to("amarr");
		shortRoute = Route.shorter().from(jita).to(amarr);
		System.out.println("from " + jita.enName() + " to " + amarr.enName() + " : short=" + shortRoute.size()
				+ " safe=" + safeRoute.size());
		for (ItemDist<SolarSystem> o : shortRoute) {
			System.out.println(" " + o.item().enName());
		}

		safeRoute = Route.safer().from(amarr).to(jita);
		shortRoute = Route.shorter().from(amarr).to(jita);
		System.out.println("from " + amarr.enName() + " to " + jita.enName() + " : short=" + shortRoute.size()
				+ " safe=" + safeRoute.size());
		for (ItemDist<SolarSystem> o : shortRoute) {
			System.out.println(" " + o.item().enName());
		}

	}

}
