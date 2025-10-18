package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapSolarSystems;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.ASpace;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.SolarSystemGroup;
import fr.guiguilechat.jcelechat.model.formula.space.Universe;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class SolarSystem extends SolarSystemGroup<EmapSolarSystems> {

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

	protected SolarSystem(DataSource datasource, int id, EmapSolarSystems source) {
		super(datasource, id, source);
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

	protected SolarSystem(int id, EmapSolarSystems source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return source().enName();
	}

	@Override
	protected Position makePosition() {
		return source().position;
	}

	@Getter(lazy = true)
	private final Constellation constellation = datasource().constellations().of(source().constellationID);

	@Getter(lazy = true)
	private final Set<Constellation> constellations = Set.of(constellation());

	@Getter(lazy = true)
	private final Collection<Planet> planets = datasource().planets().of(source().planetIDs);

	@Getter(lazy = true)
	private final Region region = datasource().regions().of(source().regionID);

	@Getter(lazy = true)
	private final Star star = datasource().stars().of(source().starID);

	@Getter(lazy = true)
	private final Collection<Stargate> stargates = datasource().stargates().of(source().stargateIDs);

	/** list all the systems that have an active stargate, sorted by id asc */
	public static List<SolarSystem> jumpableSystems(DataSource datasource) {
		return datasource.stargates().all().stream()
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

	@Getter(lazy = true)
	private final Set<SolarSystem> solarSystems = Set.of(this);

	@Override
	protected Stream<SolarSystem> streamAdjacentSolarSystems() {
		return stargates().stream()
				.map(sg -> sg.destination().solarSystem());
	}

	/**
	 * stream the elements that can impact the min/max position : only planets,
	 * stargates. This means the moons, asteroid belts, stations are not accounted
	 * for.
	 *
	 * @return
	 */
	public Stream<ASpace<?>> streamInSpaces() {
		return Stream.of(
				planets().stream(),
				stargates().stream())
				.flatMap(s -> s);
	}

	// analyzis

	public boolean isKS() {
		return region().universe() == Universe.Eve;
	}

	public double truesec() {
		return securityStatus().doubleValue();
	}

	/**
	 * represents the intervention from Concord. HS means
	 * concord will destroy you, LS means turrets will defend you, and NS means
	 * you gonna die helplessly
	 */
	public enum ConcordStatus {
		HS, LS, NS;

		public static ConcordStatus of(double truesec) {
			return truesec > 0.45 ? ConcordStatus.HS : truesec <= 0 ? ConcordStatus.NS : ConcordStatus.LS;
		}
	}

	@Getter(lazy = true)
	private final ConcordStatus concord = ConcordStatus.of(truesec());

	public boolean isHS() {
		return isKS() && truesec() > 0.45;
	}

	public boolean isLS() {
		return isKS() && 0 < truesec() && truesec() <= 0.45;
	}

	public boolean isNS() {
		return isKS() && 0 >= truesec();
	}

}
