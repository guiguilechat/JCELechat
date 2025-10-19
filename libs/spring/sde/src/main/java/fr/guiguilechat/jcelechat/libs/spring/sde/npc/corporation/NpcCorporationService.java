package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class NpcCorporationService extends SdeEntityService<NpcCorporation, Integer, NpcCorporationRepository> {

	public NpcCorporationService() {
		super(NpcCorporation::new);
	}

}
