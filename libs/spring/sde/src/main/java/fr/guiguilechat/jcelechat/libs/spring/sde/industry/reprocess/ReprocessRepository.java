package fr.guiguilechat.jcelechat.libs.spring.sde.industry.reprocess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;

public interface ReprocessRepository extends JpaRepository<Reprocess, Long> {

	@Modifying
	@Query("delete from #{#entityName}")
	void delete();

	List<Reprocess> findByReprocessed(Type reprocessed);

	List<Reprocess> findByProduct(Type product);

}
