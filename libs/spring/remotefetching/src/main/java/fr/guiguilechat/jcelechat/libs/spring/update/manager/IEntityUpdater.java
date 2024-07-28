package fr.guiguilechat.jcelechat.libs.spring.update.manager;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public interface IEntityUpdater {

	/**
	 * @return actual class name. Used to avoid proxy name when called from outside
	 *           service
	 */
	public default String fetcherName() {
		return getClass().getSimpleName();
	}

	@Getter
	@Setter
	@ToString()
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
	}

	public UpdateConfig getUpdate();

	public boolean fetch();

}
