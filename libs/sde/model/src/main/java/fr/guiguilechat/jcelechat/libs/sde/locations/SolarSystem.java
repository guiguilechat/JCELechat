package fr.guiguilechat.jcelechat.libs.sde.locations;

import java.math.BigDecimal;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapSolarSystems;
import fr.guiguilechat.jcelechat.libs.sde.locations.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.locations.generic.ALocation;
import lombok.Getter;

@Getter
public class SolarSystem extends ALocation<EmapSolarSystems> {

	public static final Mapper<EmapSolarSystems, SolarSystem> CACHE = new Mapper<>(EmapSolarSystems.LOADER,
			SolarSystem::new);

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
	private final Region region = Region.CACHE.of(source().regionID);

}
