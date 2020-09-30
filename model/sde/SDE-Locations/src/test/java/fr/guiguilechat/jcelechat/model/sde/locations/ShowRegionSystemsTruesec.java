package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowRegionSystemsTruesec {

	public static void main(String[] args) {
		Collection<String> regionNames = Region.EMPIRE_ANGELS;
		boolean nexts = false;
		if (args != null && args.length > 0 && args[0].length() > 0) {
			regionNames = Arrays.asList(args);
		}
		List<Region> regionsL = regionNames.stream().map(Region::getRegion).collect(Collectors.toList());
		Stream<Region> regions = regionsL.stream();
		if (nexts) {
			for (Region reg : regionsL) {
				regions = Stream.concat(regions, reg.adjacentRegions.stream().map(Region::getRegion));
			}
		}
		regions.distinct().flatMap(Region::systems).sorted((s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase()))
		.map(SolarSystem::getSystem)
		.forEach(s -> System.out.println(s.name + "\t" + s.truesec + "\t" + s.constellation));
	}

}
