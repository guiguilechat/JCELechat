package fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids;

import fr.guiguilechat.jcelechat.model.sde.items.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.items.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.items.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.items.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.StasisWeb;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.MutaplasmidFamily;

public class MutaWebTest extends MutaplasmidFamily {

	private static final Object[][] DATATABLE = {
			{ 47746, 47748, 47747, 0 },
			{ Cpu.INSTANCE, 0.95, 1.25, 0.85, 1.3, 0.8, 1.5, 0, 0 },
			{ SpeedFactor.INSTANCE, 0.97, 1.035, 0.95, 1.07, 0.9, 1.1, 0, 0 },
			{ MaxRange.INSTANCE, 0.95, 1.075, 0.9, 1.15, 0.8, 1.2, 0, 0 },
			{ CapacitorNeed.INSTANCE, 1.4, 1.8, 1.0, 2.0, 0.9, 2.5, 0, 0 } };

	protected MutaWebTest() {
		super(StasisWeb.load().values().stream().filter(web -> web.name.contains("Civilian")), DATATABLE);
	}

	public static final MutaWebTest INSTANCE = new MutaWebTest();

	@Override
	public String toString() {
		return "WebTest" + "";
	}

}
