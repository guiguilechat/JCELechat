package fr.guiguilechat.jcelechat.libs.evestager;

import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionStager;

public class EveChocoStagerMain {

	public static void main(String[] args) {
		IRegionStager.show(ChocoStager.INSTANCE, "Hek", 2, true);
		IRegionStager.show(ChocoStager.INSTANCE, "Hek", 3, true);
		// IRegionStager.show(EveChocoStager.INSTANCE, "Hek", 4, false);
	}

}
