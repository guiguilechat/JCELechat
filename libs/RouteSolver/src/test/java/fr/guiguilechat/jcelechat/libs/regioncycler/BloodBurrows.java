package fr.guiguilechat.jcelechat.libs.regioncycler;

import java.util.ArrayList;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.libs.regioncycler.IRegionCycler.Params;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class BloodBurrows extends AMain {

	public static void main(String[] args) {
		new BloodBurrows().run("Conoban");
	}

	@Override
	protected Predicate<SolarSystem> important() {
		return ss -> ss.truesec > 0.85 && Region.EMPIRE_BLOODS.contains(ss.region);
	}

	@Override
	protected IRegionCycler impl() {
		return GreedyDERegionCycler.INSTANCE;
	}

	@Override
	protected Params params() {
		ArrayList<String> regions = new ArrayList<>(Region.EMPIRE);
		return super.params().withRegion(regions).withAllowed(ss -> ss.isHS());
	}

}
