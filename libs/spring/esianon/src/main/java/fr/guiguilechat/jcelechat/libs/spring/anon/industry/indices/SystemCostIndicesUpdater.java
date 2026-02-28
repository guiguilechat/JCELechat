package fr.guiguilechat.jcelechat.libs.spring.anon.industry.indices;

import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.industry.costindices")
@Getter
@Setter
@Service
public class SystemCostIndicesUpdater implements EntityUpdater {

	@Lazy
	private final SystemCostIndicesRepository repo;

	@Getter
	private final UpdateConfig update = new UpdateConfig();

	private Instant nextUpdate = null;

	/** lastmodified of last fetch */
	private Instant previousLastModified = null;

	@Transactional
	@Override
	public boolean updatePulse() {
		if (nextUpdate != null && nextUpdate.isAfter(Instant.now())) {
			return false;
		}
		if (previousLastModified == null) {
			// service restarted
			previousLastModified = repo.maxLastModified();
			if (previousLastModified == null) {
				// no data already
				previousLastModified = Instant.EPOCH;
			}
		}
		Requested<R_get_industry_systems[]> ret =
				ESIRawPublic.INSTANCE
						.get_industry_systems(null);
		if (ret.isOk()) {
			Instant expires = ret.getExpiresInstant();
			Instant lastModified = ret.getLastModifiedInstant();
			if (!lastModified.equals(previousLastModified)) {
				long endMillis = lastModified.toEpochMilli();
				long durationMillis = expires.toEpochMilli() - lastModified.toEpochMilli();
				// delay between previous and present fetches
				long fetchDelay = lastModified.toEpochMilli() - previousLastModified.toEpochMilli();
				// use actual observed lastModified if possible, but not if the fetch was
				// impossible for too long.
				if (fetchDelay < 1.5 * durationMillis) {
					durationMillis = fetchDelay;
				}
				Instant periodStart = Instant.ofEpochMilli(endMillis - durationMillis),
						periodMid = Instant.ofEpochMilli(endMillis - durationMillis / 2),
						periodEnd = lastModified;
				var translated =
						Stream.of(ret.getOK())
								.map(r -> SystemCostIndices.of(r, periodStart, periodMid, periodEnd))
								.toList();
				log.debug(" saving new {} data for period (start={}, mid={}, end={})",
						translated.size(),
						periodStart,
						periodMid,
						periodEnd);
				repo.deleteByPeriondEnd(periodEnd);
				repo.saveAllAndFlush(translated);
			} else {
				// we are fetching the same data, do nothing. The execution will be rescheduled
				// a bit later.
				log.debug(" received data with same last-modified as previously received {} , wait until to {}",
						lastModified, expires);
			}
			nextUpdate = expires;
			previousLastModified = lastModified;
			return false;
		}
		nextUpdate = Instant.now().plusSeconds(5 * 60);
		return true;
	}

	@Override
	public Instant nextPulse(boolean remain, Instant now) {
		if (nextUpdate != null) {
			return nextUpdate;
		}
		return EntityUpdater.super.nextPulse(remain, now);
	}

}
