package fr.guiguilechat.jcelechat.libs.spring.update.manager;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * General interface for service which update local entities.
 */
public interface EntityUpdater extends EntityService {

	/** access the prefix to set its properties at launch. Used to debug config */
	default String propertiesPrefix() {
		ConfigurationProperties annotation = getClass().getDeclaredAnnotation(ConfigurationProperties.class);
		if (annotation != null) {
			if (annotation.prefix() != null) {
				return annotation.prefix();
			}
			if (annotation.value() != null) {
				return annotation.value();
			}
		}
		return "";
	}

	@Getter
	@Setter
	public static class UpdateConfig {

		/**
		 * if true, skip the fetch. If false, never skip. if null, use
		 * RemoteResourceUpdaterService value
		 */
		private Boolean skip = null;

		/** max number of fetch each cycle */
		private int max = 1000;

		/** if we have this number or more remain errors from esi, use max updates */
		private int errorsForMax = 90;

		/**
		 * if we have this number or less remaining errors from esi, we skip the
		 * fetching
		 */
		private int errorsMin = 10;

		/** minimum delay, in s, between two fetch cycles. Ignored if &lt;0 */
		private int delay = 0;

		/** maximum queries per second for this service. */
		private float rate = 1000;

		/**
		 * delay to wait for next fetch cycle when there is no update. Ignored if lower
		 * than {@link #getDelay()}
		 */
		private int delayUpdated = 60;

		/**
		 * base delay before retrying, per response code.
		 */
		private Map<Integer, Integer> delayBase = new HashMap<>(Map.of(
				401, 12 * 3600 // 12h to wait when banned
				, 403, 3600 // wait 1 hour when not authorized
				, 404, 3600 // wait 1 hour when data removed
		));

		/**
		 * incremental delay before retrying, per response code. Defaults to 3600s (1h)
		 * for 4xx, 1 min for others
		 */
		private Map<Integer, Integer> delayInc = new HashMap<>(Map.of(
				401, 0// no incremental wait for banned.
				, 403, 3600 // wait 1 hour per error when not authorized
				, 404, 3600 // wait 1 hour per error when data removed
		));

		@Override
		public String toString() {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				return objectMapper.writeValueAsString(this);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * just set a private field to implement
	 *
	 * <pre>{@code
	 * @Getter
	 * private final UpdateConfig update = new UpdateConfig();
	 * }</pre>
	 */
	UpdateConfig getUpdate();

	default String propertiesAsString() {
		try {
			ObjectMapper om = JsonMapper.builder().configure(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES, true).build();
			return om.writeValueAsString(Map.of(
					"update", getUpdate()));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * get the actual base delay from the config
	 */
	default int delayBase(int responseCode) {
		Integer ret = getUpdate().getDelayBase().get(responseCode);
		if (ret == null) {
			ret = responseCode / 100 == 4 ? 60 : 1;
		}
		return ret;
	}

	/**
	 * get the actual incremental delay (per error) from the config
	 */
	default int delayInc(int responseCode) {
		Integer ret = getUpdate().getDelayInc().get(responseCode);
		if (ret == null) {
			ret = responseCode / 100 == 4 ? 3600 : 60;
		}
		return ret;
	}

	/**
	 * @return true if more data are to be updated the moment this call exits (that
	 *         is, if the update was partial)
	 */
	boolean updatePulse();

	/**
	 * deduce the next Instant to fetch based on previous execution of
	 * {@link #updatePulse()}
	 *
	 * @param remain    result of last fetch
	 * @param startTime time when the fetch started
	 * @return Instant after which next fetch can be performed
	 */
	default Instant nextPulse(boolean remain, Instant now) {
		int delay = getUpdate().getDelay();
		if (delay < 0) {
			delay = 0;
		}
		if (!remain) {
			int delayUpdated = getUpdate().getDelayUpdated();
			if (delayUpdated > 0) {
				delay += delayUpdated;
			}
		}
		return now.plusSeconds(delay);
	}


}
