package fr.guiguilechat.jcelechat.libs.regioncycler;

import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.libs.regioncycler.IRegionCycler.Params;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class AmarrCycleRefuges extends AMain {

	public static void main(String[] args) {
		new AmarrCycleRefuges().run("Amarr");
	}

	@Override
	protected IRegionCycler impl() {
		return GreedyDERegionCycler.INSTANCE;
	}

	@Override
	protected Predicate<SolarSystem> important() {
		return Params.IMPORTANT_REFUGES;
	}

	@Override
	protected Params params() {
		return super.params().withRegion(Region.EMPIRE_SANSHAS);
	}

}
