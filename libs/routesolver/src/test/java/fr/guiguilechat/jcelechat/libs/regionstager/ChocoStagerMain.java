package fr.guiguilechat.jcelechat.libs.regionstager;

import fr.guiguilechat.jcelechat.libs.regionstager.IRegionStager.Params;

public class ChocoStagerMain {

	public static void main(String[] args) {
		// IRegionStager.show(ChocoStager.INSTANCE, "Hek",
		// Params.clusters(2).withSquareDistance().withDebug());
		IRegionStager.show(ChocoStager.INSTANCE, "Hek", Params.clusters(5).withSquareDistance()
				.withMustInclude("Nakugard", "Orduin").withRegions("Heimatar", "Molden Heath"));
		// IRegionStager.show(EveChocoStager.INSTANCE, "Hek", 4, false);
	}

}
