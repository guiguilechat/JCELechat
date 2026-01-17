package fr.guiguilechat.jcelechat.libs.spring.update.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DeducedEntityRepository<Entity extends DeducedEntity<Id>, Id extends Number>
extends JpaRepository<Entity, Id> {

	/**
	 * list ids existing among a list.
	 *
	 * @param ids
	 * @return
	 */
	@Query("select id from #{#entityName} where id in :ids")
	List<Id> findExistingIds(Iterable<Id> ids);

	@Query("select id from #{#entityName} where id = :id")
	Id findExistingId(Id id);

}
