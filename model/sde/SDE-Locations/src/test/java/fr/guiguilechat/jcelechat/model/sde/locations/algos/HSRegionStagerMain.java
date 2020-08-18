package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionStager.Params;

public class HSRegionStagerMain {

	public static void main(String[] args) {
		// IRegionStager.show(HSRegionStager.INSTANCE, "Orien", 2, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Orien", 1, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Gelfiven", 1, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Eredan", 1, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Eredan", 2, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Eredan", 3, false);
		IRegionStager.show(BasicRegionStager.INSTANCE, "Hek", Params.clusters(2));
		IRegionStager.show(BasicRegionStager.INSTANCE, "Hek", Params.clusters(3));
		IRegionStager.show(BasicRegionStager.INSTANCE, "Hek", Params.clusters(4));
	}


}
