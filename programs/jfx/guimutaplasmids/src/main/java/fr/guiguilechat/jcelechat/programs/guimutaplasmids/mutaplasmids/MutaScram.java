package fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids;

import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.types.module.WarpScrambler;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.MutaplasmidFamily;

public class MutaScram extends MutaplasmidFamily {

	private static final Object[][] DATATABLE = {
			{ 47729, 47731, 47730, 0 },
			{ Cpu.INSTANCE, 0.95, 1.25, 0.85, 1.3, 0.8, 1.5, 0, 0 },
			{ MaxRange.INSTANCE, 0.95, 1.075, 0.9, 1.15, 0.8, 1.2, 0, 0 },
			{ CapacitorNeed.INSTANCE, 1.4, 1.8, 1.0, 2.0, 0.9, 2.5, 0, 0 } };

	protected MutaScram() {
		super(
				WarpScrambler.METAGROUP.load().values().stream()
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
