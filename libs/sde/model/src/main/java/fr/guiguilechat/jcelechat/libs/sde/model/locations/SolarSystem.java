package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapSolarSystems;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AInspace;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class SolarSystem extends AInspace<EmapSolarSystems> {

	public static final NamingMapper<EmapSolarSystems, SolarSystem> CACHE = new NamingMapper<>(
			EmapSolarSystems.LOADER, SolarSystem::new, SolarSystem::enName);

	private final boolean border;
	private final boolean corridor;
	private final List<Integer> disallowedAnchorCategories;
	private final List<Integer> disallowedAnchorGroups;
	private final int factionID;
	private final boolean fringe;
	private final boolean hub;
	private final boolean international;
	private final BigDecimal luminosity;
	private final BigDecimal radius;
	private final boolean regional;
	private final String securityClass;
	private final BigDecimal securityStatus;
	private final String visualEffect;
	private final int wormholeClassID;

	protected SolarSystem(int id, EmapSolarSystems source) {
		super(id, source);
		border = source.border;
		corridor = source.corridor;
		factionID = source.factionID;
		disallowedAnchorCategories = source.disallowedAnchorCategories;
		disallowedAnchorGroups = source.disallowedAnchorGroups;
		fringe = source.fringe;
		hub = source.hub;
		international = source.international;
		luminosity = source.luminosity;
		radius = source.radius;
		regional = source.regional;
		securityClass = source.securityClass;
		securityStatus = source.securityStatus;
		visualEffect = source.visualEffect;
		wormholeClassID = source.wormholeClassID;
	}

	@Override
	protected String makeEnName() {
		return source().enName();
	}

	@Getter(lazy = true)
	private final Constellation constellation = Constellation.CACHE.of(source().constellationID);

	@Getter(lazy = true)
	private final List<Planet> planets = Planet.CACHE.of(source().planetIDs);

	@Getter(lazy = true)
	private final Region region = Region.CACHE.of(source().regionID);

	@Getter(lazy = true)
	private final Star star = Star.CACHE.of(source().starID);

	@Getter(lazy = true)
	private final List<Stargate> stargates = Stargate.CACHE.of(source().stargateIDs);

	/** list all the systems that have an active stargate, sorted by id asc */
	public static List<SolarSystem> jumpableSystems() {
		return Stargate.CACHE.all().stream()
				.filter(st -> st.solarSystem().id() < st.destination().solarSystem().id())
				.flatMap(st -> Stream.of(st.solarSystem(), st.destination().solarSystem()))
				.distinct()
				.sorted(Comparator.comparing(SolarSystem::id))
				.toList();
	}

	@Override
	public String toString() {
		return enName() + "(" + id() + ")";
	}

}
