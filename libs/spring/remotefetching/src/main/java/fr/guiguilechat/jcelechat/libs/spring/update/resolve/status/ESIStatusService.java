package fr.guiguilechat.jcelechat.libs.spring.update.resolve.status;

import java.time.Instant;
import java.util.Objects;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.GlobalErrors;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.TokenBucketResolver;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * get the status of the ESI.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ESIStatusService {

	private final GlobalErrors globalErrors;

	private final TokenBucketResolver tokensBucket;

	Instant nextUpdate = null;

	protected R_get_status lastResult = null;

	protected synchronized void updateStatus() {
		if (nextUpdate == null || nextUpdate.isBefore(Instant.now())) {
			Requested<R_get_status> esiAccessReq = ESIRawPublic.INSTANCE.get_status(null);
			globalErrors.processResponse(esiAccessReq);
			tokensBucket.processResponse(esiAccessReq);
			if (esiAccessReq.isOk()) {
				lastResult = esiAccessReq.getOK();
				updateNext(esiAccessReq);
			} else {
				lastResult = null;
				int delay_s = switch (esiAccessReq.getResponseCode()) {
				case 429 -> esiAccessReq.getRetryAfter();
				case 520 -> esiAccessReq.getErrorsReset();
				default -> 30;
				};
				nextUpdate = Instant.now().plusSeconds(delay_s);
				log.trace("status is error {}, nextupdate={}", esiAccessReq.getResponseCode(), nextUpdate);
			}
		}
	}

	protected void updateNext(Requested<R_get_status> response) {
		Instant fromBucket = tokensBucket.queryAvailAt();
		Instant expires = switch (response.getResponseCode()) {
		case 200:
		case 304:
			yield response.getExpiresInstant();
		default:
			yield null;
		};
		if (expires != null && fromBucket != null) {
			nextUpdate = expires.isBefore(fromBucket) ? fromBucket : expires;
		} else if (expires != null) {
			nextUpdate = expires;
		} else if (fromBucket != null) {
			nextUpdate = fromBucket;
		} else {
			int delayMS = Objects.requireNonNullElse(tokensBucket.avgQueryDelayMS(), 10000);
			nextUpdate = Instant.now().plusMillis(delayMS);
		}
	}

	public R_get_status getStatus() {
		updateStatus();
		return lastResult;
	}

	public boolean isOk() {
		R_get_status st = getStatus();
		return st != null && !st.vip;
	}

}
