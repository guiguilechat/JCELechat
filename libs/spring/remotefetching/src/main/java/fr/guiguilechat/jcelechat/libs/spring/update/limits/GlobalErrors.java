package fr.guiguilechat.jcelechat.libs.spring.update.limits;

import java.time.Instant;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import lombok.extern.slf4j.Slf4j;

/**
 * manage the global errors of ESI
 *
 * @see https://developers.eveonline.com/blog/error-rate-limiting-imminent
 */
@Slf4j
@Service
public class GlobalErrors {

	public static final int DEFAULT_ERRORS = 100;

	private Instant lastProcessedDate = null;

	private int lastRemainingErrors = DEFAULT_ERRORS;

	private Instant lastErrorsReset = null;

	synchronized void checkReset() {
		if (lastErrorsReset != null && lastErrorsReset.isBefore(Instant.now())) {
			lastRemainingErrors = DEFAULT_ERRORS;
			lastErrorsReset = null;
		}
	}

	public synchronized void processResponse(Requested<?> response) {
		if (response == null) {
			return;
		}
		// don't process a response that is older than the last processed one.
		if (lastProcessedDate != null) {
			Instant responseDate = response.getDateInstant();
			if (responseDate == null || responseDate.isBefore(lastProcessedDate)) {
				return;
			}
		}

		if (response.getResponseCode() == 420 && lastErrorsReset != null) {
			lastRemainingErrors = 0;
			lastErrorsReset = response.getErrorsResetInstant();
		} else {
			Integer remaining = response.getRemainingErrors();
			if (remaining == null) {
				return;
			}
			lastRemainingErrors = remaining;
			lastErrorsReset = remaining > 0 ? null : response.getErrorsResetInstant();
		}
		Instant responseDate = response.getDateInstant();
		if (responseDate != null) {
			lastProcessedDate = responseDate;
		}
		log.trace("processed response code={} url={} remaining={} reset={}",
				response.getResponseCode(),
				response.getURL(),
				lastRemainingErrors,
				lastErrorsReset);
	}


	public void processResponse(Iterable<Requested<?>> responses) {
		processResponse(Requested.lastErrorLimit(responses));
	}

	public int availErrors() {
		checkReset();
		return lastRemainingErrors;
	}

	public Instant getErrorReset() {
		return lastErrorsReset;
	}

}
