package fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractRegionService
		extends
		RemoteEntityService<ContractRegion, Integer, ContractRegionRepository> {

	@Lazy
	private final RegionService regionService;

	@Override
	protected ContractRegion create(Integer entityId) {
		ContractRegion ret = new ContractRegion();
		ret.setId(entityId);
		ret.setRegion(regionService.createIfAbsent(entityId));
		return ret;
	}

}
