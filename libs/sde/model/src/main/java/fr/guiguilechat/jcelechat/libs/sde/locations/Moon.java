package fr.guiguilechat.jcelechat.libs.sde.locations;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapMoons;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.locations.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.locations.generic.AOrbitingCelestial;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Moon extends AOrbitingCelestial<EmapMoons> {

	public static final Mapper<EmapMoons, Moon> CACHE = new Mapper<>(EmapMoons.LOADER,
			Moon::new);

	protected Moon(int id, EmapMoons source) {
		super(id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	@Getter(lazy = true)
	private final List<Station> stations = Station.CACHE.of(source().npcStationIDs);

}
