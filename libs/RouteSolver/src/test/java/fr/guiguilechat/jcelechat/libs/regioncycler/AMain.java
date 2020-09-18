package fr.guiguilechat.jcelechat.libs.regioncycler;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler.Params;

public abstract class AMain {

	protected abstract IRegionCycler impl();

	protected abstract Predicate<SolarSystem> important();

	protected Params params() {
		return Params.empty().withImportant(important());
	}

	public void run(String source) {
		Params params = params();
		LinkedHashMap<SolarSystem, Integer> res = impl().list(SolarSystem.getSystem(source), params);
		System.out.println("#" + res.values().stream().mapToInt(i -> i).sum() + " : " + res);
	}
}
