package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgentRepository extends JpaRepository<Agent, Integer> {

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
	left join EsiUniverseStation station  on agent.locationId = station.id
	left join EsiUniverseSolarSystem solsys on station.solarSystem=solsys or agent.locationId = solsys.id
	left join EsiAffiliationsCorporationInfo corpinfo on agent.corporationId = corpinfo.id
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
