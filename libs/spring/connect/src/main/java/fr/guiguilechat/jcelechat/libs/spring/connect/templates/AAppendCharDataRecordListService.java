package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecord;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecordList;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRecordRepository;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRepository;

public abstract class AAppendCharDataRecordListService <
	Entity extends ACharDataRecordList<Fetched, ListRecord>,
	Fetched,
	Repository extends ICharDataRepository<Entity>,
	ListRecord extends ACharDataRecord<?, Entity>,
	RecordRepo extends ICharDataRecordRepository<Entity, ListRecord>>
extends ACharDataRecordListService<Entity, Fetched, Repository, ListRecord, RecordRepo> {

	@Override
	protected void updateFromResponseOk(Entity data, Requested<Fetched[]> response) {
		data.updateMeta(response);
		Fetched[] arr = response.getOK();
		// TODO Auto-generated method stub
		super.updateFromResponseOk(data, response);
		// TODO another implementation that updates old entries and insert news only
		// Map<Long, Fetched> fetchedById = extractRemoteIds(arr);
		// Map<Long, ListRecord> storedById =
		// recordRepo().findAllByFetchResourceCharacterId(data.getCharacterId()).stream()
		// .collect(Collectors.toMap(ACharDataRecord::getRemoteId, r -> r));
		// if (newRecordStrategy() == ON_NEW_RECORD.UPDATE_OLD_APPEND_NEW) {
		// recordRepo().saveAll(
		// storedById.values().stream()
		// .peek(rec -> updateRecord(fetchedById.get(rec.getId()), rec))
		// .toList());
		// }
		// recordRepo().saveAll(
		// fetchedById.entrySet().stream()
		// .filter(e -> !storedById.containsKey(e.getKey()))
		// .map(e -> transformRecord(e.getValue()))
		// .toList());
	}

}
