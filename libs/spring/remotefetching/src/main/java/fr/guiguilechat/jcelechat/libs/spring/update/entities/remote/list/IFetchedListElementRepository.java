package fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IFetchedListElementRepository<
		ListType extends AFetchedList<?, ?, ?>,
		ListElement extends AFetchedListElement<?, ListType>,
		IdType extends Number>
		extends JpaRepository<ListElement, IdType> {

	default void removeForFetcher(Iterable<ListType> fetchResources) {
		List<? extends Number> ids = StreamSupport.stream(fetchResources.spliterator(), false)
				.map(AFetchedList::getId)
				.toList();
		deleteByFetchResourceIdIn(ids);
	}

	/**
	 * fast delete. Does not update the cache, so does not return the list of items
	 * deleted
	 */
	@Modifying
	@Query("delete from #{#entityName} where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

	List<ListElement> findAllByFetchResourceId(int id);

	List<ListElement> findAllByFetchResourceIn(Iterable<ListType> fetchResources);

}
