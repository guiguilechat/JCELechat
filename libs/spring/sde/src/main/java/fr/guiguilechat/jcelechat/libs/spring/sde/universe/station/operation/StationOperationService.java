package fr.guiguilechat.jcelechat.libs.spring.sde.universe.station.operation;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class StationOperationService extends SdeEntityService<StationOperation, Integer, StationOperationRepository> {

	public StationOperationService() {
		super(StationOperation::new);
	}

}
