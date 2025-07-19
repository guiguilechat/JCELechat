package fr.guiguilechat.jcelechat.libs.spring.update.batch;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetch.BatchItem;
import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetch.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.IEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.status.ESIStatusService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BatchFetchService<
	Fetch extends BatchFetch<Item>,
	FetchRepo extends BatchFetchRepository<Fetch>,
    Item extends BatchItem<Fetch, ?>,
    ItemRepo extends JpaRepository<Item, ?>, Structure
>
    implements IEntityUpdater {


	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private ESIStatusService esiStatusService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private FetchRepo fetchRepository;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private ItemRepo itemRepository;


	@Getter
	private final UpdateConfig update = new UpdateConfig();

	/**
	 * following fetch date, if any. If not set, default method to use the
	 * {@link UpdateConfig}. It is reset during the {@link #preUpdate()}
	 */
	@Setter(value = AccessLevel.PROTECTED)
	private Instant nextUpdate = null;

	@Override
	public Instant nextUpdate(boolean remain, Instant now) {
		if (nextUpdate != null) {
			return nextUpdate;
		}
		return IEntityUpdater.super.nextUpdate(remain, now);
	}

	protected abstract Requested<Structure> fetchData(Map<String, String> properties);

	protected abstract Fetch createFetch();

	public Fetch lastSuccess() {
		return fetchRepository().findTop1ByStatusOrderByLastModifiedDesc(STATUS.SUCCESS);
	}


	@Override
	public boolean fetch() {
		Fetch lastSuccess = lastSuccess();
		if (lastSuccess != null && Instant.now().isBefore(lastSuccess.getExpires())) {
			return false;
		}
		int errorsRemain = esiStatusService().availErrors();
		if (errorsRemain < getUpdate().getErrorsMin()) {
			setNextUpdate(esiStatusService().getErrorReset());
			return true;
		}

		log.debug("{} fetching data with last etag {}", fetcherName(), lastSuccess == null ? null : lastSuccess.getEtag());
		Map<String, String> properties = new HashMap<>();
		if (lastSuccess != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastSuccess.getEtag());
		}
		Fetch fetchResult = createFetch();
		fetchResult = fetchRepository().save(fetchResult);
		Requested<Structure> response = fetchData(properties);
		if (response == null) {
			updateNullResponse(fetchResult);
		} else {
			int responseCode = response.getResponseCode();
			fetchResult.setResponseCode(responseCode);
			switch (responseCode) {
			case 200:
				updateOk(fetchResult, response);
				break;
			case 204: // no content => null
				updateNullBody(fetchResult, response);
				break;
			case 304:
				updateNoChange(fetchResult, response);
				break;
			default:
				switch (responseCode / 100) {
				case 4:
					updateRequestError(fetchResult, response);
					break;
				case 5:
					updateServerError(fetchResult, response);
					break;
				default:
					log.error("{} while fetching, received response code {} and error {}",
					    fetcherName(), responseCode, response.getError());
					throw new UnsupportedOperationException("case " + responseCode + " not handled for url" + response.getURL());
				}
			}
		}
		fetchRepository().saveAndFlush(fetchResult);
		return false;
	}

	protected void updateNullResponse(Fetch fetchResult) {
		fetchResult.setErrorMessage("null response");
		fetchResult.setResponseCode(0);
		fetchResult.setStatus(STATUS.ERROR);
	}

	protected void updateOk(Fetch fetchResult, Requested<Structure> response) {
		updateMetaOk(fetchResult, response);
		List<Item> converted = convert(fetchResult, response.getOK());
		converted.forEach(it -> it.setFetch(fetchResult));
		itemRepository().saveAllAndFlush(converted);
		fetchResult.setNbItems(converted.size());
		log.debug(" {} saving {} new items", fetcherName(), converted.size());
	}

	protected void updateMetaOk(Fetch fetchResult, Requested<Structure> response) {
		fetchResult.setEtag(response.getETag());
		fetchResult.setExpires(response.getExpiresInstant());
		fetchResult.setLastModified(response.getLastModifiedInstant());
		fetchResult.setStatus(STATUS.SUCCESS);
	}

	protected abstract List<Item> convert(Fetch fetchResult, Structure response);

	private void updateNullBody(Fetch fetchResult, Requested<Structure> response) {
		updateMetaOk(fetchResult, response);
	}

	private void updateNoChange(Fetch fetchResult, Requested<Structure> response) {
		fetchResult.setStatus(STATUS.CACHED);
	}

	private void updateRequestError(Fetch fetchResult, Requested<Structure> response) {
		fetchResult.setErrorMessage(response.getError());
		fetchResult.setStatus(STATUS.ERROR);
	}

	private void updateServerError(Fetch fetchResult, Requested<Structure> response) {
		fetchResult.setErrorMessage(response.getError());
		fetchResult.setStatus(STATUS.ERROR);
	}

}
