package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedListElement;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;
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
 * @param <ListRecord>     the entity that stores the elements of the array
 *                       received.
 * @param <RecordRepo> storage for elements data
 */
@NoArgsConstructor
@Getter
public abstract class ACharDataRecordListService<
    	Entity extends AFetchedList<Integer, Fetched, ListRecord>, 
    	Fetched, 
    	Repository extends IRemoteEntityRepository<Entity, Integer>, 
    	ListRecord extends AFetchedListElement<?, Entity>, 
    	RecordRepo extends IFetchedListElementRepository<Entity, ListRecord>>
    extends AConnectedCharDataService<Entity, Fetched[], Repository> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private RecordRepo recordRepo;

	protected void updateResponseOk(Entity data, Fetched[] arr) {
		recordRepo().removeForFetcher(List.of(data));
		if (arr != null && arr.length != 0) {
			saveNewResources(data, Stream.of(arr));
		}
	}

	@Override
	protected void updateResponseOk(Map<Entity, Fetched[]> responseOk) {
		super.updateResponseOk(responseOk);
		responseOk.entrySet().stream()
		    .forEach(e -> updateResponseOk(e.getKey(), e.getValue()));
	}

	protected void saveNewResources(Entity data, Stream<Fetched> newResources) {
			recordRepo().saveAll(
		    newResources
			        .map(this::transformRecord)
			        .peek(rec -> rec.setFetchResource(data))
			        .toList());
	}

	protected abstract ListRecord transformRecord(Fetched f);

	// service use

	public List<ListRecord> list(int Id) {
		return recordRepo().findAllByFetchResourceId(Id);
	}

}
