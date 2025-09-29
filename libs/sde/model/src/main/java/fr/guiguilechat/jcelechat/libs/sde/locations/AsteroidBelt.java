package fr.guiguilechat.jcelechat.libs.sde.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapAsteroidBelts;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.locations.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.locations.generic.AOrbiting;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class AsteroidBelt extends AOrbiting<EmapAsteroidBelts> {

	public static final Mapper<EmapAsteroidBelts, AsteroidBelt> CACHE = new Mapper<>(EmapAsteroidBelts.LOADER,
			AsteroidBelt::new);

	protected AsteroidBelt(int id, EmapAsteroidBelts source) {
		super(id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

}
