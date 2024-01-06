package fr.guiguilechat.jcelechat.libs.regioncycler;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.libs.regioncycler.IRegionCycler.Params;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public abstract class AMain {

	private static final Logger logger = LoggerFactory.getLogger(AMain.class);

	protected abstract IRegionCycler impl();

	protected abstract Predicate<SolarSystem> important();

	protected Params params() {
		return Params.hsNoInvasion().withImportant(important());
	}

	public void run(String source) {
		logger.debug("start");
		long start = System.currentTimeMillis();
		Params params = params();
		LinkedHashMap<SolarSystem, Integer> res = impl().list(SolarSystem.getSystem(source), params);
		System.out.println("#" + res.values().stream().mapToInt(i -> i).sum() + "(" + res.size() + "steps)" + " : " + res);
		logger.debug("end in " + (System.currentTimeMillis() - start) + " ms");
	}
}
