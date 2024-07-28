package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedListElement;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

/**
 * Only append the new entries instead of deleting the old ones.
 */
public abstract class AAppendCharDataRecordListService <
    Entity extends AFetchedList<Integer, Fetched, ListRecord>,
	Fetched,
    Repository extends IRemoteEntityRepository<Entity, Integer>,
	ListRecord extends AFetchedListElement<?, Entity>,
	RecordRepo extends IFetchedListElementRepository<Entity, ListRecord>>
extends ACharDataRecordListService<Entity, Fetched, Repository, ListRecord, RecordRepo> {

	@Override
	protected void updateResponseOk(Entity data, Fetched[] arr) {
		Stream<Fetched> missing = arr == null || arr.length == 0 ? Stream.empty() : findMising(data, arr);
		saveNewResources(data, missing);
	}

	protected abstract Stream<Fetched> findMising(Entity data, Fetched[] arr);

}
