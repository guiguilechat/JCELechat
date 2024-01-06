package fr.guiguilechat.jcelechat.model.sde.locations;

public class ShowHSUnanchorable {
	public static void main(String[] args) {
		SolarSystem.load().values().stream().filter(sys -> sys.isHS() && sys.anchorStructures == false)
				.forEach(sys -> System.err.println(sys.name + "(" + sys.region + "/" + sys.constellation + ")"));
	}
}
