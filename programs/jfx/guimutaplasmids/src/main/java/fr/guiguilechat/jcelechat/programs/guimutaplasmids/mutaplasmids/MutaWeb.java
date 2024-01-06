package fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids;

import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.types.module.StasisWeb;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.MutaplasmidFamily;

public class MutaWeb extends MutaplasmidFamily {

	private static final Object[][] DATATABLE = {
			{ 47699, 47701, 47700, 0 },
			{ Cpu.INSTANCE, 0.95, 1.25, 0.85, 1.3, 0.8, 1.5, 0, 0 },
			{ SpeedFactor.INSTANCE, 0.97, 1.035, 0.95, 1.07, 0.9, 1.1, 0, 0 },
			{ MaxRange.INSTANCE, 0.95, 1.075, 0.9, 1.15, 0.8, 1.2, 0, 0 },
			{ CapacitorNeed.INSTANCE, 1.4, 1.8, 1.0, 2.0, 0.9, 2.5, 0, 0 } };

	protected MutaWeb() {
		super(StasisWeb.METAGROUP.load().values().stream().filter(web -> !web.name.contains("Civilian")), DATATABLE);
	}

	public static final MutaWeb INSTANCE = new MutaWeb();

	@Override
	public String toString() {
		return "Web";
	}

}
