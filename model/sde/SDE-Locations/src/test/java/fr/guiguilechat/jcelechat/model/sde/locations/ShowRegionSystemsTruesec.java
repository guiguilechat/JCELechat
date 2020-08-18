package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowRegionSystemsTruesec {

	public static void main(String[] args) {
		String[] regionName = { "Heimatar", "Metropolis", "Molden Heath" };
		boolean nexts = true;
		if (args != null && args.length > 0 && args[0].length() > 0) {
			regionName = args;
		}
		List<Region> regionsL = Stream.of(regionName).map(Region::getRegion).collect(Collectors.toList());
		Stream<Region> regions = regionsL.stream();
		if (nexts) {
			for (Region reg : regionsL) {
				regions = Stream.concat(regions, reg.adjacentRegions.stream().map(Region::getRegion));
			}
		}
		regions.distinct().flatMap(Region::systems).sorted((s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase()))
		.map(SolarSystem::getSystem)
		.forEach(s -> System.out.println(s.name + "\t" + s.truesec));
	}

}
