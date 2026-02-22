package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketRegionService
		extends
		RemoteNumberEntityService<MarketRegion, Integer, MarketRegionRepository> {

	@Override
	protected MarketRegion create(Integer entityId) {
		MarketRegion ret = new MarketRegion();
		ret.setId(entityId);
		return ret;
	}

}
