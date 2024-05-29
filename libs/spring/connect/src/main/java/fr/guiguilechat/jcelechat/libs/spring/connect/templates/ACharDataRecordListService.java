package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.AFetchedListElement;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.repositories.IFetchedListElementRepository;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.repositories.IRemoteFetchedResourceRepository;
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
    	Repository extends IRemoteFetchedResourceRepository<Entity, Integer>, 
    	ListRecord extends AFetchedListElement<?, Entity>, 
    	RecordRepo extends IFetchedListElementRepository<Entity, ListRecord>>
    extends AConnectedCharDataService<Entity, Fetched[], Repository> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private RecordRepo recordRepo;

	@Override
	protected void updateResponseOk(Entity data, Requested<Fetched[]> response) {
		updateMetaOk(data, response);
		recordRepo().deleteByFetchResource(data);
		Fetched[] arr = response.getOK();
		if (arr != null && arr.length != 0) {
			saveNewResources(data, Stream.of(arr));
		}
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

	public List<ListRecord> list(int RemoteId) {
		return recordRepo().findAllByFetchResourceRemoteId(RemoteId);
	}

}
