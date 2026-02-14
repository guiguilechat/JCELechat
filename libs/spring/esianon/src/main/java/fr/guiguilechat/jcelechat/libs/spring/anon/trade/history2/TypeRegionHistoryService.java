package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.TypeRegionHistory.TypeRegionKey;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.PulseUpdatedService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TypeRegionHistoryService
		extends
			PulseUpdatedService<TypeRegionHistory, TypeRegionKey, TypeRegionHistoryRepository> {

}
