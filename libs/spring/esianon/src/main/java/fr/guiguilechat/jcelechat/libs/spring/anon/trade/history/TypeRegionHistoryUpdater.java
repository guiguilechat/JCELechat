package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.ManagedEntityUpdater;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.history")
@Getter
@Setter
@Service
public class TypeRegionHistoryUpdater extends ManagedEntityUpdater {

	private final TypeRegionHistoryRepository repo;

	@Override
	public long nbToUpdate() {
		return repo.countByFetchActiveTrueAndFetchExpiresBefore(Instant.now());
	}

	@Override
	protected boolean fetchUpdate() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
