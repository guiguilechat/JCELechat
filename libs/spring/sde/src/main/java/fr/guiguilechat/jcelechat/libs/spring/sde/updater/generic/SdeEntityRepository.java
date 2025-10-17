package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SdeEntityRepository<Type extends SdeEntity<IdType>, IdType extends Number>
		extends JpaRepository<Type, IdType> {

	@Modifying
	@Query("""
update
	#{#entityName}
set
	removed=true
""")
	void setAllRemoved();

}
