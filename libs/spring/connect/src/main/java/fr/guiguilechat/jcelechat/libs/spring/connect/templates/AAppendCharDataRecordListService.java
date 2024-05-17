package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecord;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecordList;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRecordRepository;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRepository;

/**
 * Only append the new entries instead of deleting the old ones.
 */
public abstract class AAppendCharDataRecordListService <
	Entity extends ACharDataRecordList<Fetched, ListRecord>,
	Fetched,
	Repository extends ICharDataRepository<Entity>,
	ListRecord extends ACharDataRecord<?, Entity>,
	RecordRepo extends ICharDataRecordRepository<Entity, ListRecord>>
extends ACharDataRecordListService<Entity, Fetched, Repository, ListRecord, RecordRepo> {

	@Override
	protected void updateResponseOk(Entity data, Requested<Fetched[]> response) {
		updateMetaOk(data, response);
		Fetched[] arr = response.getOK();
		Stream<Fetched> missing = arr == null || arr.length == 0 ? Stream.empty() : findMising(data, arr);
		saveNewResources(data, missing);
	}

	protected abstract Stream<Fetched> findMising(Entity data, Fetched[] arr);

}
