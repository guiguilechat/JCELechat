package fr.guiguilechat.jcelechat.libs.sde.model.locations;

public class BoundariesJita {
	public static void main(String[] args) {
		SolarSystem jita = SolarSystem.CACHE.of("Jita");
		System.out.println("" + jita + " min/max= " + jita.min() + "/" + jita.max());

		Constellation kimotoro = jita.constellation();
		System.out.println("" + kimotoro + " min/max= " + kimotoro.min() + "/" + kimotoro.max());

		Region theForge = kimotoro.region();
		System.out.println("" + theForge + " min/max= " + theForge.min() + "/" + theForge.max());
	}

}
