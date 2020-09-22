package fr.guiguilechat.jcelechat.libs.regioncycler;

import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler.Params;

public class RegionCyclerAngelsDens extends AMain {

	public static void main(String[] args) {
		new RegionCyclerAngelsDens().run("Nakugard");
	}

	@Override
	protected IRegionCycler impl() {
		return GeneticRegionCycler.INSTANCE;
	}

	@Override
	protected Predicate<SolarSystem> important() {
		return Params.IMPORTANT_DENS;
	}

	@Override
	protected Params params() {
		return super.params().withRegion(Region.EMPIRE_ANGELS);
	}

}
