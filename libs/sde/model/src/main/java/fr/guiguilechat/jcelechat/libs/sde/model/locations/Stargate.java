package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStargates;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStargates.Destination;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AInspace;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Stargate extends AInspace<EmapStargates> {

	public static final Mapper<EmapStargates, Stargate> CACHE = new Mapper<>(EmapStargates.LOADER,
			Stargate::new);

	protected Stargate(int id, EmapStargates source) {
		super(id, source);
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
		public static Dest of(Destination source) {
			return new Dest(SolarSystem.CACHE.of(source.solarSystemID), Stargate.CACHE.of(source.stargateID));
		}
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = SolarSystem.CACHE.of(source().solarSystemID);

	@Getter(lazy = true)
	private final Dest destination = Dest.of(source().destination);

	public double distance() {
		return position().distance(destination().stargate().position());
	}

	public static double maxDistance() {
		return EmapStargates.LOADER.load().entrySet().stream()
				.filter(e -> e.getKey() < e.getValue().destination.stargateID)
				.mapToDouble(e -> e.getValue().position
						.distance(EmapStargates.LOADER.get(e.getValue().destination.stargateID).position))
				.max().getAsDouble();
	}

}
