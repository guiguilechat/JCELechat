package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IFetchedListElementRepository<
ListType extends AFetchedList<?, ?, ?>,
ListElement extends AFetchedListElement<?, ListType>
> extends JpaRepository<ListElement, Long> {

	default void removeForFetcher(Iterable<ListType> fetchResources) {
		List<? extends Number> ids = StreamSupport.stream(fetchResources.spliterator(), false)
				.map(AFetchedList::getId)
				.toList();
		deleteByFetchResourceIdIn(ids);
	}

	/**
	 * MUST be overriden in sub classes (just change to the actual entity name)
	 */
	@Modifying
	@Query("delete from EntityName where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

	List<ListElement> findAllByFetchResourceId(int id);

		List<ListElement> findAllByFetchResourceIn(Iterable<ListType> fetchResources);

}
