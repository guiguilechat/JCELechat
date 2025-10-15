package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStargates;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStargates.Destination;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.LocalCacheDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.SDEDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AInspace;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Stargate extends AInspace<EmapStargates> {

	public static final Mapper<EmapStargates, Stargate> CACHE = new Mapper<>(EmapStargates.LOADER,
			Stargate::new);

	protected Stargate(SDEDataSource datasource, int id, EmapStargates source) {
		super(datasource, id, source);
	}

	protected Stargate(int id, EmapStargates source) {
		this(LocalCacheDataSource.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	@Override
	protected Position makePosition() {
		return source().position.add(solarSystem().position());
	}

	@Override
	public String toString() {
		return solarSystem().enName() + "→" + destination().solarSystem().enName();
	}

	public static record Dest(SolarSystem solarSystem, Stargate stargate) {
		public static Dest of(SDEDataSource datasource, Destination source) {
			return new Dest(datasource.solarSystems().of(source.solarSystemID),
					datasource.stargates().of(source.stargateID));
		}
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = datasource().solarSystems().of(source().solarSystemID);

	@Getter(lazy = true)
	private final Dest destination = Dest.of(datasource(), source().destination);

	public double distance() {
		return position().distance(destination().stargate().position());
	}

}
