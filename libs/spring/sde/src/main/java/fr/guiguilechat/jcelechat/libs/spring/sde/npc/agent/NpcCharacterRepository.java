package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface NpcCharacterRepository extends SdeEntityRepository<NpcCharacter, Integer> {

	@Query("""
select
	agent,
	station,
	solsys,
	solsys.constellation,
	solsys.constellation.region,
	corpinfo
from
	#{#entityName} agent
	left join SdeUniverseStation station  on agent.locationId = station.id
	left join SdeUniverseSolarSystem solsys on station.solarSystem=solsys or agent.locationId = solsys.id
	left join EsiAffiliationsCorporationInfo corpinfo on agent.corporation.id = corpinfo.id
""")
	List<Object[]> listFullAgents();

	@Query("""
select
	agent.id
from
	#{#entityName} agent
""")
	List<Integer> listIds();

}
