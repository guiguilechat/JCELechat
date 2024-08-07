package fr.guiguilechat.jcelechat.libs.spring.update.manager;

import java.time.Instant;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * interface to implement by services which update local entities.
 */
public interface IEntityUpdater {

	/**
	 * @return actual class name. Used to avoid proxy name when called from outside
	 *           service
	 */
	public default String fetcherName() {
		return getClass().getSimpleName();
	}

	public default String propertiesPrefix() {
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

		/** if we have this number or more remain errors, use max updates */
		private int errorsForMax = 90;

		/** if we have this number or less remaining errors, we skip the fetching */
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
	public UpdateConfig getUpdate();

	/**
	 * @return true if more data are to be updated the moment this call exits (that
	 *           is, if the update was partial)
	 */
	public boolean fetch();

	/**
	 * deduce the next Instant to fetch based on previous execution of
	 * {@link #fetch()}
	 * 
	 * @param remain    result of last fetch
	 * @param startTime time when the fetch started
	 * @return Instant after which next fetch can be performed
	 */
	public default Instant nextUpdate(boolean remain, Instant now) {
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
