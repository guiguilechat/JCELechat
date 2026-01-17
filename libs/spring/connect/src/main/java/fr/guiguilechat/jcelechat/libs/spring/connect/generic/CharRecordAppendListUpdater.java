package fr.guiguilechat.jcelechat.libs.spring.connect.generic;

import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntityRepository;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.AFetchedListElementAutoId;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.IFetchedListElementRepositoryAutoId;

/**
 * Only append the new entries instead of deleting the old ones.
 */
public abstract class CharRecordAppendListUpdater <
		    Entity extends AFetchedList<Integer, Fetched, ListRecord>,
			Fetched,
		    Repository extends RemoteEntityRepository<Entity, Integer>,
			Service extends RemoteEntityService<Entity, Integer, Repository>,
			ListRecord extends AFetchedListElementAutoId<?, Entity>,
			RecordRepo extends IFetchedListElementRepositoryAutoId<Entity, ListRecord>>
	extends CharRecordListUpdater<Entity, Fetched, Repository, Service, ListRecord, RecordRepo> {

	@Override
	protected void updateResponseOk(Entity data, Fetched[] arr) {
		Stream<Fetched> missing = arr == null || arr.length == 0 ? Stream.empty() : findMising(data, arr);
		saveNewResources(data, missing);
	}

	protected abstract Stream<Fetched> findMising(Entity data, Fetched[] arr);

}
