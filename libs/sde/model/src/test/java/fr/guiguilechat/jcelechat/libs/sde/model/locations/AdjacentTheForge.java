package fr.guiguilechat.jcelechat.libs.sde.model.locations;

public class AdjacentTheForge {

	public static void main(String[] args) {
		SolarSystem jita = SolarSystem.CACHE.of("Jita");
		System.out.println("" + jita + " adjacent systems : " + jita.adjacentSolarSystems());
		System.out.println("" + jita + " adjacent constellations : " + jita.adjacentConstellations());
		System.out.println("" + jita + " adjacent regions : " + jita.adjacentRegions());

		Constellation kimotoro = jita.constellation();
		System.out.println("" + kimotoro + " adjacent constellations : " + kimotoro.adjacentConstellations());
		System.out.println("" + kimotoro + " adjacent regions : " + kimotoro.adjacentRegions());


		Region theForge = kimotoro.region();
		System.out.println("" + theForge + " adjacent regions : " + theForge.adjacentRegions());
	}

}
