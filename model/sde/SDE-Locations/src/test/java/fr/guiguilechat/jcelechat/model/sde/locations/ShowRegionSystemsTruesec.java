package fr.guiguilechat.jcelechat.model.sde.locations;

public class ShowRegionSystemsTruesec {

	public static void main(String[] args) {
		String regionName = "Metropolis";
		if (args != null && args.length > 0 && args[0].length() > 0) {
			regionName = args[0];
		}
		Region reg = Region.getRegion(regionName);
		reg.systems().sorted().map(SolarSystem::getSystem).forEach(s -> System.out.println(s.name + "\t" + s.truesec));
	}

}
