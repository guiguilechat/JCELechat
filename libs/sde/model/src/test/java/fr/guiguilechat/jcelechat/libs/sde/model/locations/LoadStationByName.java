package fr.guiguilechat.jcelechat.libs.sde.model.locations;

public class LoadStationByName {

	public static void main(String... args) {
		var theForge = Region.CACHE.of("The Forge");
		System.out.println("the forge by name : id = " + theForge.id());
		var jitaIV = Station.CACHE.of(60003760);
		System.out.println("Jita IV by id : name=" + jitaIV.enName());
		jitaIV = Station.CACHE.of("Jita IV - Moon 4 - Caldari Navy Assembly Plant");
		System.out.println("Jita IV by name : id = " + jitaIV.id());
	}

}
