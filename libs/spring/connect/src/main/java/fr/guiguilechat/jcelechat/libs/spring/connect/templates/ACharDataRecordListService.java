package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecord;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecordList;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRecordRepository;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * service to manage data corresponding to a remote character that is fetched as
 * a list
 * 
 * @param <Entity>     the entity that stores the fetch meta data
 * @param <Fetched>    elements retrieved as array for this resource from the
 *                       remote
 * @param <Repository> storage for meta data
 * @param <RecordType> the entity that stores the elements of the array
 *                       received.
 * @param <RecordRepo> storage for elements data
 */
@NoArgsConstructor
@Getter
public abstract class ACharDataRecordListService<
    Entity extends ACharDataRecordList<Fetched, RecordType>, 
    Fetched, Repository extends ICharDataRepository<Entity>,
    RecordType extends ACharDataRecord<?, Entity>,
    RecordRepo extends ICharDataRecordRepository<Entity, RecordType>
>
    extends AConnectedCharDataService<Entity, Fetched[], Repository>
{
	
	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private RecordRepo recordRepo;

	@Override
	protected void updateFromResponseOk(Entity data, Requested<Fetched[]> response) {
		data.updateMeta(response);
		recordRepo().deleteByFetchResource(data);
		Fetched[] arr = response.getOK();
		if (arr != null && arr.length > 0) {
			recordRepo().saveAll(Stream.of(arr).map(this::transformRecord).peek(rec -> rec.setFetchResource(data)).toList());
		}
	}

	protected abstract RecordType transformRecord(Fetched f);

	public List<RecordType> list(int characterId) {
		return recordRepo().findAllByFetchResourceCharacterId(characterId);
	}

}
