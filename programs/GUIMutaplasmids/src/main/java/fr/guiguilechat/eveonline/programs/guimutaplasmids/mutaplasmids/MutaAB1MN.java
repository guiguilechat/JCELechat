package fr.guiguilechat.eveonline.programs.guimutaplasmids.mutaplasmids;

import java.util.Map.Entry;

import fr.guiguilechat.eveonline.model.sde.items.attributes.SpeedFactor;
import fr.guiguilechat.eveonline.model.sde.items.types.module.PropulsionModule;
import fr.guiguilechat.eveonline.programs.guimutaplasmids.MutaplasmidFamily;

public class MutaAB1MN extends MutaplasmidFamily {

	private static final Object[][] DATATABLE = {
		{ SpeedFactor.INSTANCE, 0.97, 1.035, 0.95, 1.07, 0.9, 1.1, 0, 0 } 
	};

	protected MutaAB1MN() {
		super(PropulsionModule.load().entrySet().stream().filter(e -> e.getKey().contains(" 1mn ")).map(Entry::getValue),
				DATATABLE);
	}

	public static final MutaAB1MN INSTANCE = new MutaAB1MN();

}
