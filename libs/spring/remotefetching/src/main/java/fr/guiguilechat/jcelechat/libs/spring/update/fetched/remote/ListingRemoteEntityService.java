package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * A {@link RemoteEntityService} that also allows to list the existing ids prior
 * to updating them ( with {@link #listRemoteIds(Map)})
 *
 * @param <Entity>
 * @param <IdType>
 * @param <Fetched>
 * @param <Repository>
 */
@Slf4j
public abstract class ListingRemoteEntityService <
		Entity extends RemoteEntity<IdType, Fetched>,
		IdType extends Number,
		Fetched,
		Repository extends RemoteEntityRepository<Entity, IdType>>
	extends RemoteEntityService<Entity, IdType, Fetched, Repository> {

	/**
	 * list the remote ids that need to be managed. They will then be created
	 * locally and scheduled for update
	 *
	 * @param params parameters to be sent (typically the etag header)
	 * @return the result of requesting the list of ids
	 */
	protected abstract Requested<List<IdType>> listRemoteIds(Map<String, String> params);

	@Override
	protected Map<String, Object> propertiesMap() {
		return Map.of(
				"update", getUpdate(),
				"list", getList());
	}

	/**
	 * create the entities with id specified in the configuration at startup.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void addListInit() {
		if (list.init != null && !list.init.isEmpty()) {
			log.trace("{} init={}", fetcherName(), list.init);
			insertIfAbsent(list.init);
		}
	}

	//
	// list updating methods
	//

	@Getter
	@Setter
	@ToString()
	public class ListConfig {

		private boolean skip = false;

		private List<IdType> init = null;

		/** minimum delay, in s, between two listing. Ignored if &lt;0 */
		private int delay = 10;

	}

	@Getter
	protected final ListConfig list = new ListConfig();

	private String lastListEtag = null;
	private Instant listExpires = null;

	//
	// pre update lists new elements if required
	//

	@Override
	protected void preUpdate() {
		super.preUpdate();
		if (!list.skip && (listExpires == null || listExpires.isBefore(Instant.now()))) {
			// TODO also use a specific list token bucket ?
			int nbRemainingErrors = globalErrors().availErrors();
			if (nbRemainingErrors <= getUpdate().getErrorsMin()) {
				log.trace("{} skip pre update as only {} remaining errors", fetcherName(), nbRemainingErrors);
				return;
			}
			long startms = System.currentTimeMillis();
			log.trace("{} started listing new entries", fetcherName());
			Map<String, String> properties = new HashMap<>();
			if (lastListEtag != null) {
				properties.put(ConnectedImpl.IFNONEMATCH, lastListEtag);
			}
			Requested<List<IdType>> resp = listRemoteIds(properties);
			if (resp != null) {
				processResponse(resp);
				switch (resp.getResponseCode()) {
				case 200:
					long postFetch = System.currentTimeMillis();
					log.debug(" {} listed {} entries in {}s", fetcherName(), resp.getOK().size(),
							(postFetch - startms) / 1000);
					onNewListFetched(insertIfAbsent(resp.getOK()));
					lastListEtag = resp.getETag();
					listExpires = resp.getExpiresInstant();
					break;
				case 304:
					listExpires = resp.getExpiresInstant();
					log.trace(" {} received no list change", fetcherName());
					break;
				default:
					log.warn("update service {} received invalid response {} when requesting list of entities",
							getClass().getSimpleName(), resp);
				}
			} else {
				log.warn("update service {} received null list of entities",
						getClass().getSimpleName());
			}
			if (getList().getDelay() > 0) {
				Instant nextListFromDelay = Instant.now().plusSeconds(getList().getDelay());
				if (listExpires == null || nextListFromDelay.isAfter(listExpires)) {
					listExpires = nextListFromDelay;
				}
			}
			long endms = System.currentTimeMillis();
			log.trace("{} finished listing new entries in {}s", fetcherName(), (endms - startms) / 1000);
		}
	}

	/** called when new ids have been listed */
	protected void onNewListFetched(Set<IdType> newIds) {

	}

}
