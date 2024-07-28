package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface SolarSystemRepository extends IRemoteEntityRepository<SolarSystem, Integer> {

	@Query("""
select
	sg.destination.solarSystem
from
	EsiUniverseStargate sg
where
	sg.solarSystem=:source
""")
	public List<SolarSystem> adjacent(SolarSystem source);

	List<SolarSystem> findByNameEqualsIgnoreCase(String name);

}
