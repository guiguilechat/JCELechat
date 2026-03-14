package fr.guiguilechat.jcelechat.libs.spring.update.limits;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.RateLimitations;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * store the information retrieved from esi queries regarding the token bucket
 * for a given group, so as to share them among several services that share that
 * group
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public class TokenBucket {

	/**
	 * number of tokens we assume we have until we receive actual limit
	 * specifications
	 */
	public static final int DEFAULT_TOKENS = Integer.MAX_VALUE;

	/** start waiting when less than this ratio of tokens are avail */
	public static final double MAX_FILL_FACTOR = 0.1;

	/**
	 * the group identifies of the bucket
	 */
	private final String group;

	private Instant lastProcessedDate = null;

	private RateLimitations lastRateLimits = null;

	private int lastRemainingTokens = DEFAULT_TOKENS;

	private Instant lastRetryAfter = null;

	public synchronized void processResponse(Requested<?> response) {
		if (response == null) {
			return;
		}

		// don't process a response without a date
		// can happen when there was an error on the way
		Instant responseDate = response.getDateInstant();
		if (responseDate == null) {
			return;
		}

		// only process response within that group
		String responseGroup = response.getRateLimitGroup();
		if (!group.equals(responseGroup)) {
			return;
		}

		// don't process a response that is older than the last processed one.
		if (lastProcessedDate != null) {
			if (responseDate == null || responseDate.isBefore(lastProcessedDate)) {
				return;
			}
		}

		// update the specs every time, because they can dynamically change
		String spec = response.getRateLimitLimit();
		if (spec == null) {
			log.warn("bucket {} received request for {} without a rate limit specification",
					responseGroup,
					response.getURL());
			return;
		}
		RateLimitations limits = RateLimitations.parse(spec);
		if (limits == null) {
			log.error("could not parse rate limit specs {} from request url {}", spec, response.getURL());
			return;
		}

		// now actually process
		lastProcessedDate = responseDate;
		lastRateLimits = limits;

		if (response.getResponseCode() == 429) {
			if (lastRetryAfter != null) {
				lastRemainingTokens = 0;
				lastRetryAfter = response.getRetryAfterInstant();
				log.trace("reached rate limit, set retry after to {}", lastRetryAfter);
			} else {
				log.trace("already reached rate limit, discarding code {} until {}", response.getResponseCode(),
						lastRetryAfter);
				// we already know we are in rate limit, no need to update the last retry
			}
		} else {
			Integer remaining = response.getRateLimitRemaining();
			if (remaining == null) {
				// WTF ?
				log.warn("url {} received null rate limit remaining but rate limit group {}, discarding",
						response.getURL(), responseGroup);
				lastRemainingTokens = DEFAULT_TOKENS;
				return;
			}
			lastRemainingTokens = remaining;
			lastRetryAfter = null;
			if (lastRateLimits != null) {
				if (remaining < MAX_FILL_FACTOR * lastRateLimits.windowTokens()) {
					lastRetryAfter =
							Instant.now()
									.plusSeconds((long) Math.ceil(MAX_FILL_FACTOR * lastRateLimits.windowDurationS()));
					log.trace("low {} remaining for group {} with spec {} : wait until {}",
							remaining,
							group,
							lastRateLimits,
							lastRetryAfter);
				}
			} else if (remaining <= 10) {
				log.trace("replaced low remaining {} with fixed value to workaround missing retry-after", remaining);
				lastRemainingTokens = 10;
			}
		}
		log.trace("bucket {} processed response {}:{} limitations={} ; remaining={} retryAfter={}",
				responseGroup,
				response.getResponseCode(),
				response.getURL(),
				spec,
				lastRemainingTokens,
				lastRetryAfter);
	}

	/**
	 * @return the max tokens per window, if received already, or
	 *         {@link #DEFAULT_TOKENS}
	 */
	protected int maxWindowTokens() {
		RateLimitations rateLimits = lastRateLimits;
		if (rateLimits != null) {
			return rateLimits.windowTokens();
		}
		return DEFAULT_TOKENS;
	}

	public synchronized void checkReset() {
		if (lastRetryAfter != null && lastRetryAfter.isBefore(Instant.now())) {
			lastRetryAfter = null;
			lastRemainingTokens = maxWindowTokens();
		}
	}

	/**
	 * process a batch of responses. Only the one with last server date will be
	 * actually processed.
	 */
	public void processResponse(Iterable<Requested<?>> responses) {
		processResponse(Requested.lastRateLimitGroup(responses, group));
	}

	/**
	 * @return the number of queries that can be performed right now
	 */
	public int availTokens() {
		checkReset();
		return lastRetryAfter == null ? lastRemainingTokens : 0;
	}

	/**
	 * @return time at which we can start sending queries
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
