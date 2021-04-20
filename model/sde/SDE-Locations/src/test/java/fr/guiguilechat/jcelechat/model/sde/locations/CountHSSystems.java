package fr.guiguilechat.jcelechat.model.sde.locations;

public class CountHSSystems {

	public static void main(String[] args) {
		System.out.println(SolarSystem.load().values().stream().filter(sys -> sys.isHS()).count());

	}

}
