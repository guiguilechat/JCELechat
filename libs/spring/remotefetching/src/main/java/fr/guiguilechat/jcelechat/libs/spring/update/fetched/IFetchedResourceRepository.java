package fr.guiguilechat.jcelechat.libs.spring.update.fetched;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IFetchedResourceRepository<Entity extends AFetchedResource<Id>, Id extends Number>
extends JpaRepository<Entity, Id> {

	/**
	 * list ids existing among a list.
	 *
	 * @param ids
	 * @return
	 */
	@Query("select id from #{#entityName} where id in :ids")
	List<Id> findExistingIds(Iterable<Id> ids);

}
