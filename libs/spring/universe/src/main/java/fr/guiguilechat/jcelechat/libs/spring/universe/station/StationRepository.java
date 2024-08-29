package fr.guiguilechat.jcelechat.libs.spring.universe.station;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.EntityGraph;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface StationRepository extends IRemoteEntityRepository<Station, Integer> {

	@EntityGraph(attributePaths = { "solarSystem" })
	public Stream<Station> findAllByIdIn(Iterable<Integer> ids);

}
