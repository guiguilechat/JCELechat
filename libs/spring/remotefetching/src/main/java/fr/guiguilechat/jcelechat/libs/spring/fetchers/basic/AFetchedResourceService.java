package fr.guiguilechat.jcelechat.libs.spring.fetchers.basic;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.status.ESIStatusService;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.tools.ExecutionService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class AFetchedResourceService<
		Entity extends AFetchedResource<Id>,
		Id,
		Repository extends IFetchedResourceRepository<Entity, Id>> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter
	private Repository repo;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter
	private ExecutionService executionService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter
	private ESIStatusService esiStatusService;

	/**
	 * @return actual class name. Used to avoid proxy name when called from outside
	 *           service
	 */
	public String fetcherName() {
		return getClass().getSimpleName();
	}

	/**
	 * if new entries should be activated when created. Default true.<br />
	 * Can be changed with eg
	 * 
	 * <pre>{@code
	 * @Getter(lazy = true)
	 * private final boolean activateNewEntry = false;
	 * }</pre>
	 */
	protected boolean isActivateNewEntry() {
		return true;
	}

	protected abstract Entity create(Id entityId);

	//
	//
	//

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

	@Getter
	private final UpdateConfig update = new UpdateConfig();

	public abstract long nbToUpdate();

	private Instant nextUpdateTime = null;
	/** stored here to avoid counting when delay not reached */
	private boolean lastMoreToUpdate = true;

	public boolean fetch() {
		// skip if delay not met
		if (nextUpdateTime != null && nextUpdateTime.isAfter(Instant.now())) {
			return lastMoreToUpdate;
		}

		preUpdate();
		update();
		postUpdate();

		long nbToUpdate = nbToUpdate();
		// create delay to next
		int delay = Math.max(getUpdate().getDelay(), 0);
		if (getUpdate().getDelayUpdated() > delay && nbToUpdate == 0) {
			delay = getUpdate().getDelayUpdated();
			log.debug(" {} no more data to update({}), extended delay {}s", fetcherName(), nbToUpdate, delay);
		}
		nextUpdateTime = Instant.now().plusSeconds(delay);
		lastMoreToUpdate = nbToUpdate > 0;
		return lastMoreToUpdate;
	}

	// cleanup, fetch if exists new elements
	protected void preUpdate() {

	}

	// actually update the udpatable data
	protected abstract void update();

	// save stats
	protected void postUpdate() {
	}

}
