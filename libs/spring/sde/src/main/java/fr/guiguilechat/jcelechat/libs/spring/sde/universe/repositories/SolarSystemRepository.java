package fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;

public interface SolarSystemRepository extends JpaRepository<SolarSystem, Integer> {

	@Query("""
select
	sg.destination.solarSystem
from
	SdeUniverseStargate sg
where
	sg.solarSystem=:source
""")
	public List<SolarSystem> adjacent(@Param("source") SolarSystem source);

	public List<SolarSystem> findByConstellation(Constellation constellation);

}
