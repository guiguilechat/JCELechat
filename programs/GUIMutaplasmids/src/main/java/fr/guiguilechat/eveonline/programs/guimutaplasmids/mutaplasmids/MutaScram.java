package fr.guiguilechat.eveonline.programs.guimutaplasmids.mutaplasmids;

import fr.guiguilechat.eveonline.model.sde.items.attributes.CapacitorNeed;
import fr.guiguilechat.eveonline.model.sde.items.attributes.Cpu;
import fr.guiguilechat.eveonline.model.sde.items.attributes.MaxRange;
import fr.guiguilechat.eveonline.model.sde.items.types.module.WarpScrambler;
import fr.guiguilechat.eveonline.programs.guimutaplasmids.MutaplasmidFamily;

public class MutaScram extends MutaplasmidFamily {

	private static final Object[][] DATATABLE = {
			{ Cpu.INSTANCE, 0.95, 1.25, 0.85, 1.3, 0.8, 1.5, 0, 0 },
			{ MaxRange.INSTANCE, 0.95, 1.075, 0.9, 1.15, 0.8, 1.2, 0, 0 },
			{ CapacitorNeed.INSTANCE, 1.4, 1.8, 1.0, 2.0, 0.9, 2.5, 0, 0 } };

	protected MutaScram() {
		super(WarpScrambler.load().values().stream()
				.filter(point -> point.name.contains("Scrambler") && !point.name.contains("Heavy")
						&& !point.name.contains("CONCORD")
						&& !point.name.contains("Civilian")),
				DATATABLE);
	}

	public static final MutaScram INSTANCE = new MutaScram();

	@Override
	public String toString() {
		return "Scrambler";
	}

}
