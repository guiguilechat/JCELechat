package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.Comparator;

public class SortSystemsByMoons {

	public static void main(String[] args) {
		SolarSystem.CACHE.all().stream().sorted(Comparator.comparing(SortSystemsByMoons::nbMoons))
				.forEach(ss -> System.out.println(ss.enName() + "\t" + nbMoons(ss)));
	}

	public static long nbMoons(SolarSystem ss)  {
		return ss.planets().stream().flatMap(p->p.moons().stream()).count();
	}

}
