package fr.guiguilechat.jcelechat.libs.denlicker;

import fr.guiguilechat.jcelechat.libs.regioncycler.ChocoRegionCycler;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler.Params;

public class DenLickerMain {

	public static void main(String[] args) {
		IRegionCycler impl = ChocoRegionCycler.INSTANCE;
		// impl = GreedyDenLicker.INSTANCE;
		String source = "Faktun";
		Params params = Params.empty();

		source = "Nakugard";
		params.withRegion("Heimatar", "Molden heath");

		System.err.println(impl.list(SolarSystem.getSystem(source), params));
	}

}
