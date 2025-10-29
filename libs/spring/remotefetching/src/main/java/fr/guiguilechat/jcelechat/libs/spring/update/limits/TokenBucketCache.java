package fr.guiguilechat.jcelechat.libs.spring.update.limits;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * cache of {@link TokenBucket} , by group
 */
@Service
public class TokenBucketCache {

	private Map<String, TokenBucket> cache = new HashMap<>();

	public TokenBucket bucket(String group) {
		synchronized (cache) {
			return cache.computeIfAbsent(group, TokenBucket::new);
		}
	}

}
