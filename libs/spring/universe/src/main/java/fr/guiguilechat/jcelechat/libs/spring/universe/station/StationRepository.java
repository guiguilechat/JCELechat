package fr.guiguilechat.jcelechat.libs.spring.universe.station;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.EntityGraph;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface StationRepository extends SdeEntityRepository<Station, Integer> {

	@EntityGraph(attributePaths = { "solarSystem" }) Stream<Station> findAllByIdIn(Iterable<Integer> ids);

}
