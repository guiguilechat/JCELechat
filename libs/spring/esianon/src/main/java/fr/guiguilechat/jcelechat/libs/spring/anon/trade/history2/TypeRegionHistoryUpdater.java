package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.TypeRegionHistory.TypeRegionKey;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.PulseUpdatedUpdater;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.history")
@Getter
@Setter
//@Service
public class TypeRegionHistoryUpdater extends
		PulseUpdatedUpdater<TypeRegionHistory, TypeRegionKey, TypeRegionHistoryRepository, TypeRegionHistoryService> {

	@Override
	protected boolean updateNextBatch() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
