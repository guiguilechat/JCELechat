package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SdeEntityRepository<Entity extends SdeEntity<IdType>, IdType extends Number>
		extends JpaRepository<Entity, IdType> {

	@Modifying
	@Query("""
update
	#{#entityName}
set
	removed=true
""")
	void setAllRemoved();

	List<Entity> findAllByReceivedFalse();

	List<Entity> findAllByReceivedTrueAndRemovedFalse();

	Entity findByIdAndReceivedTrueAndRemovedFalse(IdType id);

	List<Entity> findAllByIdInAndReceivedTrueAndRemovedFalse(Iterable<IdType> ids);

	/** utility */
	default Map<IdType, Entity> mapById(Collection<Entity> entities) {
		return entities.stream()
				.collect(Collectors.toMap(SdeEntity::getId, o -> o));
	}

	/**
	 * @return all entities by id, irrelevant of their status
	 */
	default Map<IdType, Entity> mapAllById() {
		return mapById(findAll());
	}

	/**
	 * @return all the entities that are received and not removed.
	 */
	default Map<IdType, Entity> mapActiveById() {
		return mapById(findAllByReceivedTrueAndRemovedFalse());
	}

	default Map<IdType, Entity> mapOfIdById(Iterable<IdType> ids) {
		return mapById(findAllById(ids));
	}

	/**
	 * @return all the entities that are received and not removed, for given ids.
	 */
	default Map<IdType, Entity> mapActiveOfIdById(Iterable<IdType> ids) {
		return mapById(findAllByIdInAndReceivedTrueAndRemovedFalse(ids));
	}

}
