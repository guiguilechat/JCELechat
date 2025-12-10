package fr.guiguilechat.jcelechat.libs.spring.update.limits;

import java.time.Instant;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.RateLimitations;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * process the queries for ONE path, trying to find the token bucket it belongs
 * to. Once found, will delegate limit values to it ; until then uses default
 * values.
 *
 * @see https://developers.eveonline.com/blog/hold-your-horses-introducing-rate-limiting-to-esi
 */
@Component
@Scope("prototype")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TokenBucketResolver {

	private final TokenBucketCache cache;

	private TokenBucket bucket = null;

	/**
	 * process a response. Deduce the bucket if group is present, then delegate.
	 */
	public synchronized void processResponse(Requested<?> response) {
		if (bucket == null) {
			if (response.getDate() == null) {
				return;
			}
			String group = response.getRateLimitGroup();
			if (group == null) {
				return;
			}
			bucket = cache.bucket(group);
			log.trace("deduced group: " + group);
		}
		bucket.processResponse(response);
	}

	/**
	 * process a batch of responses. deduce the bucket if group is present, then
	 * delegate.
	 */
	public synchronized void processResponse(Iterable<Requested<?>> responses) {
		if (bucket == null) {
			String group = StreamUtils.createStreamFromIterator(responses.iterator())
					.filter(r -> r.getDate() != null)
					.map(Requested::getRateLimitGroup)
					.filter(s -> s != null)
					.findAny().orElse(null);
			if (group == null) {
				// no request had a group so we can't allocate a bucket. ignore.
				return;
			}
			bucket = cache.bucket(group);
			log.debug("deduced group: \"{}\"", group);
		}
		bucket.processResponse(responses);
	}

	private static final int TOKEN_PER_QUERY = 2;

	/**
	 * @return the number of queries that can be performed right now
	 */
	public int availQueries() {
		return (bucket == null ? TokenBucket.DEFAULT_TOKENS : bucket.availTokens()) / TOKEN_PER_QUERY;
	}

	/**
	 * @return time at which we can start sending queries
	 */
	public Instant queryAvailAt() {
		return bucket == null ? Instant.now() : bucket.queryAvailAt();
	}

	public Integer avgQueryDelayMS() {
		if (bucket == null) {
			return null;
		}
		RateLimitations lastRateLimits = bucket.getLastRateLimits();
		if (lastRateLimits == null) {
			return null;
		}
		return lastRateLimits.avgTokenDelayMS() * TOKEN_PER_QUERY;
	}

}
