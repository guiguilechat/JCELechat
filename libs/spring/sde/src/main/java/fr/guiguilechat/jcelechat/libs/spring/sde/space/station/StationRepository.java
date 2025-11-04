package fr.guiguilechat.jcelechat.libs.spring.sde.space.station;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.EntityGraph;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface StationRepository extends SdeEntityRepository<Station, Integer> {

	@EntityGraph(attributePaths = { "moon.planet", "operation", "owner", "planet", "solarSystem", "star" })
	Stream<Station> findAllByIdIn(Iterable<Integer> ids);

}
