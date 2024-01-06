package fr.guiguilechat.jcelechat.model.sde.locations;

public class CountHSSystems {

	public static void main(String[] args) {
		System.out.println(" HS : " + SolarSystem.load().values().stream().filter(sys -> sys.isHS()).count());
		System.out.println(" LS : " + SolarSystem.load().values().stream().filter(sys -> sys.isLS()).count());
		System.out.println(" NS : " + SolarSystem.load().values().stream().filter(sys -> sys.isNS()).count());
		System.out.println(" WS : " + SolarSystem.load().values().stream().filter(sys -> sys.isWormhole).count());
		System.out.println(
				" other : " + SolarSystem.load().values().stream().filter(sys -> !sys.isKS && !sys.isWormhole).count());

	}

}
