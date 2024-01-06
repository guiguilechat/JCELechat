package fr.guiguilechat.jcelechat.libs.regionstager;

import fr.guiguilechat.jcelechat.libs.regionstager.IRegionStager.Params;

public class HSRegionStagerMain {

	public static void main(String[] args) {
		// IRegionStager.show(HSRegionStager.INSTANCE, "Orien", 2, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Orien", 1, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Gelfiven", 1, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Eredan", 1, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Eredan", 2, false);
		// IRegionStager.show(HSRegionStager.INSTANCE, "Eredan", 3, false);
		IRegionStager.show(BruteStager.INSTANCE, "Hek", Params.clusters(2));
		IRegionStager.show(BruteStager.INSTANCE, "Hek", Params.clusters(3));
		IRegionStager.show(BruteStager.INSTANCE, "Hek", Params.clusters(4));
	}


}
