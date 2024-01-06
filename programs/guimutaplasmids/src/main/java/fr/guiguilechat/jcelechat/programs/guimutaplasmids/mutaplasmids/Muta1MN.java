package fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids;

import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.types.module.PropulsionModule;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.MutaplasmidFamily;

public class Muta1MN extends MutaplasmidFamily {

	private static final Object[][] DATATABLE = {
			{ 47746, 47748, 47747, 0 },
			{ SpeedFactor.INSTANCE, 0.97, 1.035, 0.95, 1.07, 0.9, 1.1, 0, 0 },
			{ Cpu.INSTANCE, 0.95, 1.25, 0.85, 1.3, 0.8, 1.5, 0, 0 },
			{ Power.INSTANCE, 0.95, 1.25, 0.85, 1.3, 0.8, 1.5, 0, 0 },
			{ CapacitorNeed.INSTANCE, 0.85, 1.2, 0.8, 1.3, 0.6, 1.4, 0, 0 }
	};

	protected Muta1MN() {
		super(
				PropulsionModule.METAGROUP.load().values().stream()
				.filter(pm -> pm.name.contains("1MN ") && !pm.name.contains("Civilian")),
				DATATABLE);
	}

	public static final Muta1MN INSTANCE = new Muta1MN();

	@Override
	public String toString() {
		return "1MN";
	}

}
