package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketRegionService
		extends
		RemoteNumberEntityService<MarketRegion, Integer, MarketRegionRepository> {

	@Lazy
	private final MarketLineService marketLineService;

	@Lazy
	private final RegionService regionService;

	@Lazy
	private final SolarSystemService solarSystemService;

	@Lazy
	private final TypeService typeService;

	@Override
	protected MarketRegion create(Integer entityId) {
		MarketRegion ret = new MarketRegion();
		ret.setId(entityId);
		ret.setRegion(regionService.createIfAbsent(entityId));
		return ret;
	}

}
