package fr.guiguilechat.jcelechat.libs.spring.update.limits;

import java.time.Instant;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * manages the token bucket for ONE path
 *
 * @see https://developers.eveonline.com/blog/hold-your-horses-introducing-rate-limiting-to-esi
 */
@Component
@Scope("prototype")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TokensBucket {

	private Instant lastProcessedDate = null;

	private int lastRemainingTokens = 100;

	private Instant lastRetryAfter = null;

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

		if (response.getResponseCode() == 429 && lastRetryAfter != null) {
			lastRemainingTokens = 0;
			lastRetryAfter = response.getRetryAfterInstant();
		} else {
			Integer remaining = response.getRateLimitRemaining();
			if (remaining == null) {
				return;
			}
			lastRemainingTokens = remaining;
			lastRetryAfter = null;// should check if remaining=0 but then the next avail bucket is not in the
									// headers.
		}
		Instant responseDate = response.getDateInstant();
		if (responseDate != null) {
			lastProcessedDate=responseDate;
		}
		log.trace("processed response {} group {} {} with code {}, lastRemaining={} lastRetryAfter={}",
				response.getURL(),
				response.getRateLimitGroup(),
				response.getRateLimitLimit(),
				response.getResponseCode(),
				lastRemainingTokens,
				lastRetryAfter);
	}

	public synchronized void checkReset() {
		if (lastRetryAfter != null && lastRetryAfter.isBefore(Instant.now())) {
			lastRetryAfter = null;
			lastRemainingTokens = 100;
		}
	}

	/**
	 * process a batch of responses. Only the one with last server date will be
	 * actually processed.
	 */
	public void processResponse(Iterable<Requested<?>> responses) {
		processResponse(Requested.lastOf(responses));
	}

	private static final int TOKEN_PER_QUERY = 2;

	/**
	 * @return the number of queries that can be performed right now
	 */
	public int availQueries() {
		checkReset();
		return lastRemainingTokens / TOKEN_PER_QUERY;
	}

	/**
	 * @return
	 */
	public Instant queryAvailAt() {
		checkReset();
		// copy it to avoid parallel updates issues
		Instant selfReset = lastRetryAfter;
		if (selfReset == null) {
			return Instant.now();
		}
		return selfReset;
	}

}
