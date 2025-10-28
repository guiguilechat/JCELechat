package fr.guiguilechat.jcelechat.libs.spring.update.resolve.status;

import java.time.Instant;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.GlobalErrors;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.TokensBucket;
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

	private final TokensBucket tokensBucket;

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
				int delay_s = switch (esiAccessReq.getResponseCode()) {
				case 429 -> esiAccessReq.getRetryAfter();
				case 520 -> esiAccessReq.getErrorsReset();
				default -> 30;
				};
				nextUpdate = Instant.now().plusSeconds(delay_s);
			}
		}
	}

	protected void updateNext(Requested<R_get_status> response) {
		// TODO use the x-ratelimit-limit instead (600/150m means max 600 per 15×60=900
		// s)
		Instant expires = response.getExpiresInstant();
		Instant plus10 = Instant.now().plusSeconds(10);
		if (expires == null || expires.isBefore(plus10)) {
			nextUpdate = plus10;
		} else {
			nextUpdate=expires;
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
