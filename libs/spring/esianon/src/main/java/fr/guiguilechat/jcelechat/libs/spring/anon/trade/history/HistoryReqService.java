package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Getter
@Setter
public class HistoryReqService
		extends RemoteEntityService<HistoryReq, Long, HistoryReqRepository> {

	@Lazy
	private final RegionService regionService;

	@Lazy
	private final TypeService typeService;

	@Override
	protected HistoryReq create(Long entityId) {
		Region region = regionService.createIfAbsent(HistoryReq.getRegionId(entityId));
		Type type = typeService.createIfAbsent(HistoryReq.getTypeId(entityId));
		return new HistoryReq(region, type);
	}

	public void prioritizeType(int typeId) {
		prioritize(repo().findByTypeId(typeId));
	}

}
