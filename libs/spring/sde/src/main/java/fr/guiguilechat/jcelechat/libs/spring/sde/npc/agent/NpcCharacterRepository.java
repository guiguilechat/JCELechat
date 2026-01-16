package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface NpcCharacterRepository extends SdeEntityRepository<NpcCharacter, Integer> {

	@Query("""
select
	agent.id
from
	#{#entityName} agent
""")
	List<Integer> listIds();

}
