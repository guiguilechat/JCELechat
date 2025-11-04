package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.util.List;

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

}
