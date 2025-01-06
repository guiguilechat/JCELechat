package fr.guiguilechat.jcelechat.libs.spring.update.fetched;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IFetchedResourceRepository<Entity extends AFetchedResource<Id>, Id extends Number>
    extends JpaRepository<Entity, Id> {

	//// made to be faster than the base findAllById that iterates over the elements
	// public List<Entity> findAllByIdIn(Collection<Id> ids);

}
